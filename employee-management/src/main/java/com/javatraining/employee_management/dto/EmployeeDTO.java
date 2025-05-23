package com.javatraining.employee_management.dto;

import java.util.Date;

public class EmployeeDTO {
    private String name;
    private String email;
    private Double salary;
    private String department;
    private Date dateOfJoining;

    public EmployeeDTO(){

    }
    public EmployeeDTO(String name, String email,String department,Double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}
