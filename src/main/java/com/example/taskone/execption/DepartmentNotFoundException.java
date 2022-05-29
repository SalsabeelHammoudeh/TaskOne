package com.example.taskone.execption;

public class DepartmentNotFoundException extends Throwable {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
