package com.example.taskone.dto;

import com.example.taskone.model.Address;
import lombok.Data;

@Data
public class EmployeeDto {
    private Integer id;
    private String name;
    private int age;
    private String role;
    private boolean gender;
    private double baseSalary;
    private Address address = new Address();
}
