package com.javatraining.employee_management.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDTOTest {

    @Test
    void testGetterSetter() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Name");
        dto.setEmail("email@example.com");
        dto.setSalary(45000.0);
        dto.setDepartment("dept");

        assertEquals("Name", dto.getName());
        assertEquals("email@example.com", dto.getEmail());
        assertEquals(45000.0, dto.getSalary());
        assertEquals("dept", dto.getDepartment());
    }

    @Test
    void testParameterizedConstructor() {
        EmployeeDTO dto = new EmployeeDTO("Name", "email@mail.com", "dept", 75000.0);
        assertEquals("Name", dto.getName());
        assertEquals("email@mail.com", dto.getEmail());
        assertEquals("dept", dto.getDepartment());
        assertEquals(75000.0, dto.getSalary());
    }
}
