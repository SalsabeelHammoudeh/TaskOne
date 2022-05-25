package com.example.taskone.execption;

public class EmployeeNotFoundException extends Throwable {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
