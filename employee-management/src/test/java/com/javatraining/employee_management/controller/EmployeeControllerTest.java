package com.javatraining.employee_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.dto.MessageOutDto;
import com.javatraining.employee_management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testCreate() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Name");

        MessageOutDto response = new MessageOutDto("Employee created");

        when(employeeService.create(any(EmployeeDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/employees/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Employee created"));
    }

    @Test
    void testGetById() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Name");

        when(employeeService.get(1)).thenReturn(dto);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void testGetAll() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Name");

        when(employeeService.getAll()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/employees/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void testUpdate() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Updated Name");

        MessageOutDto response = new MessageOutDto("Employee updated");

        when(employeeService.update(eq(1), any(EmployeeDTO.class))).thenReturn(response);

        mockMvc.perform(patch("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee updated"));
    }

    @Test
    void testDelete() throws Exception {
        MessageOutDto response = new MessageOutDto("Employee deleted");

        when(employeeService.delete(1)).thenReturn(response);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee deleted"));
    }

    @Test
    void testImportFromCsv() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "employees.csv", "text/csv", "id,name\n1,Test".getBytes());

        MessageOutDto response = new MessageOutDto("CSV imported");

        when(employeeService.importFromCsv(any())).thenReturn(response);

        mockMvc.perform(multipart("/api/employees/import").file(file))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("CSV imported"));
    }
}