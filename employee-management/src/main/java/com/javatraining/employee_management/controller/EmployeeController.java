package com.javatraining.employee_management.controller;

import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.dto.MessageOutDto;
import com.javatraining.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // Create a new employee
    @PostMapping("/register")
    public ResponseEntity<MessageOutDto> create(@RequestBody EmployeeDTO dto) {
        MessageOutDto created = employeeService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> get(@PathVariable int id) {
        EmployeeDTO employee = employeeService.get(id);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    // Get all employees
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAll() {
        List<EmployeeDTO> employees = employeeService.getAll();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    // Update employee by ID
    @PatchMapping("/{id}")
    public ResponseEntity<MessageOutDto> update(@PathVariable int id, @RequestBody EmployeeDTO dto) {
        MessageOutDto updated = employeeService.update(id, dto);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageOutDto> delete(@PathVariable int id) {
        MessageOutDto response = employeeService.delete(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // Import employees from CSV
    @PostMapping("/import")
    public ResponseEntity<MessageOutDto> importFromCsv(@RequestParam("file") MultipartFile file) {
        MessageOutDto message = employeeService.importFromCsv(file);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

