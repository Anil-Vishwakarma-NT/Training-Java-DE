package com.javatraining.employee_management.serviceImp;

import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.dto.MessageOutDto;
import com.javatraining.employee_management.entity.Employee;
import com.javatraining.employee_management.exception.ResourceConflictException;
import com.javatraining.employee_management.exception.ResourceNotFoundException;
import com.javatraining.employee_management.mapper.EmployeeMapper;
import com.javatraining.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class EmployeeServiceImpTest {
    @InjectMocks
    private EmployeeServiceImp employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    private Employee employee;
    private EmployeeDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto = new EmployeeDTO("Name", "email@example.com","dept",50000.0);
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Name");
        employee.setEmail("email@examle.com");
        employee.setDepartment("dept");
        employee.setSalary(5000.0);
    }


    @Test
    void create_shouldRegisterEmployeeSuccessfully() {
        when(employeeRepository.findByEmailIgnoringCase(dto.getEmail())).thenReturn(Optional.empty());
        when(employeeMapper.toEntity(dto)).thenReturn(employee);

        MessageOutDto result = employeeService.create(dto);

        assertEquals("Employee Registered Successfully", result.getMessage());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void create_shouldThrowConflictIfEmailExists() {
        when(employeeRepository.findByEmailIgnoringCase(dto.getEmail())).thenReturn(Optional.of(employee));

        assertThrows(ResourceConflictException.class, () -> employeeService.create(dto));
    }

    @Test
    void get_shouldReturnEmployeeDTO() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeMapper.toDTO(employee)).thenReturn(dto);

        EmployeeDTO result = employeeService.get(1);

        assertEquals("Name", result.getName());
    }

    @Test
    void get_shouldThrowIfNotFound() {
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.get(1));
    }

    @Test
    void getAll_shouldReturnListOfEmployeeDTOs() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        when(employeeMapper.toDTO(any(Employee.class))).thenReturn(dto);

        List<EmployeeDTO> result = employeeService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    void getAll_shouldThrowIfEmpty() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.getAll());
    }

    @Test
    void update_shouldUpdateAndReturnSuccessMessage() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        MessageOutDto result = employeeService.update(1, dto);

        assertEquals("Employee Updated Successfully", result.getMessage());
    }

    @Test
    void delete_shouldRemoveEmployee() {
        when(employeeRepository.existsById(1)).thenReturn(true);

        MessageOutDto result = employeeService.delete(1);

        assertEquals("Employee deleted Successfully", result.getMessage());
    }

    @Test
    void delete_shouldThrowIfEmployeeDoesNotExist() {
        when(employeeRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.delete(1));
    }

    @Test
    void importFromCsv_shouldImportEmployeesSuccessfully() throws IOException {
        String csvData = "name,email,department,salary\nName,email@example.com,dept,50000";
        MockMultipartFile file = new MockMultipartFile("file", "employees.csv", "text/csv", csvData.getBytes());

        MessageOutDto result = employeeService.importFromCsv(file);

        assertEquals("Employee Registered Successfully from the csv file", result.getMessage());
        verify(employeeRepository, times(1)).saveAll(anyList());
    }
}
