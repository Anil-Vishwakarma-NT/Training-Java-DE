package com.javatraining.employee_management.repository;

import com.javatraining.employee_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findById(int id);
    Optional<Employee> findByEmailIgnoringCase(String email);
}
