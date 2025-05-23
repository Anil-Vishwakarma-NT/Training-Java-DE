package com.javatraining.employee_management.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "department", nullable = false)
    private String department;
    @Column(name = "joining_date")
    private Date dateOfJoining;

    public Employee() {

    }

    public Employee(String name, String email, Double salary, String department, Date dateOfJoining) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return id;
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

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
