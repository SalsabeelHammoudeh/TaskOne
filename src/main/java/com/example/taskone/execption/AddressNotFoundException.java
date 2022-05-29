package com.example.taskone.execption;

public class AddressNotFoundException extends Throwable {
    public AddressNotFoundException(String message) {
        super(message);
    }
}
