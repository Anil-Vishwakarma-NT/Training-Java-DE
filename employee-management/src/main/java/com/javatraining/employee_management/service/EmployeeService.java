package com.javatraining.employee_management.service;

import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.dto.MessageOutDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    MessageOutDto create(EmployeeDTO dto);
    EmployeeDTO get(int id);
    List<EmployeeDTO> getAll();
    MessageOutDto update(int id, EmployeeDTO dto);
    MessageOutDto delete(int id);
    MessageOutDto importFromCsv(MultipartFile file);
}
