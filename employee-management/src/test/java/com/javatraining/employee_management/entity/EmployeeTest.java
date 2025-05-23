package com.javatraining.employee_management.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    void testGettersAndSetters() {
        Employee employee = new Employee();
        Date date = new Date();

        employee.setId(1L);
        employee.setName("Name");
        employee.setEmail("email@example.com");
        employee.setSalary(60000.0);
        employee.setDepartment("Dept");
        employee.setDateOfJoining(date);

        assertEquals(1L, employee.getId());
        assertEquals("Name", employee.getName());
        assertEquals("email@example.com", employee.getEmail());
        assertEquals(60000.0, employee.getSalary());
        assertEquals("Dept", employee.getDepartment());
        assertEquals(date, employee.getDateOfJoining());
    }

    @Test
    void testParameterizedConstructor() {
        Date date = new Date();
        Employee employee = new Employee("Name", "email@mail.com", 70000.0, "Dept", date);

        assertEquals("Name", employee.getName());
        assertEquals("email@mail.com", employee.getEmail());
        assertEquals(70000.0, employee.getSalary());
        assertEquals("Dept", employee.getDepartment());
        assertEquals(date, employee.getDateOfJoining());
    }
}