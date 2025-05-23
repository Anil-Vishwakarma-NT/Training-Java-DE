package com.javatraining.employee_management.serviceImp;

import com.javatraining.employee_management.dto.EmployeeDTO;
import com.javatraining.employee_management.dto.MessageOutDto;
import com.javatraining.employee_management.entity.Employee;
import com.javatraining.employee_management.exception.ResourceConflictException;
import com.javatraining.employee_management.exception.ResourceNotFoundException;
import com.javatraining.employee_management.mapper.EmployeeMapper;
import com.javatraining.employee_management.repository.EmployeeRepository;
import com.javatraining.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public MessageOutDto create(EmployeeDTO dto) {
        if(employeeRepository.findByEmailIgnoringCase(dto.getEmail()).isPresent()){
            throw new ResourceConflictException("employee with this email already exist");
        }

        Employee employee = employeeMapper.toEntity(dto);

        employeeRepository.save(employee);
        return new MessageOutDto("Employee Registered Successfully");
    }

    @Override
    public EmployeeDTO get(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<EmployeeDTO> employeeList =  employeeRepository.findAll().stream().map(employeeMapper::toDTO)
                .collect(Collectors.toList());

        if(employeeList.isEmpty()){
            throw new ResourceNotFoundException("Employee does not Exist");
        }
        return employeeList;
    }

    @Override
    public MessageOutDto update(int id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found ID: "+id));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dto.getDepartment());

        employeeRepository.save(employee);
        return new MessageOutDto("Employee Updated Successfully");
    }

    @Override
    public MessageOutDto delete(int id) {
        if(!employeeRepository.existsById(id)){
            throw new ResourceNotFoundException("Employee not found with ID: " + id);
        }
        employeeRepository.deleteById(id);

        return new MessageOutDto("Employee deleted Successfully");
    }

    @Override
    public MessageOutDto importFromCsv(MultipartFile file) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){

            List<Employee> employees = reader.lines()
                    .skip(1)
                    .map(line -> {
                        String[] parts = line.split(",");
                        Employee employee = new Employee();
                        employee.setName(parts[0].trim());
                        employee.setEmail(parts[1].trim());
                        employee.setDepartment(parts[2].trim());
                        employee.setSalary(Double.parseDouble(parts[3].trim()));
                        return employee;
                    }).collect(Collectors.toList());
            employeeRepository.saveAll(employees);

        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("File Not found");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return new MessageOutDto("Employee Registered Successfully from the csv file");
    }
}
