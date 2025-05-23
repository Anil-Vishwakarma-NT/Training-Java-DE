package com.javatraining.employee_management.mapper;

import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setSalary(employee.getSalary());
        dto.setDepartment(employee.getDepartment());
        return dto;

    }

    public Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dto.getDepartment());
        return employee;
    }
}
