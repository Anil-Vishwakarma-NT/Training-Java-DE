package com.javatraining.employee_management.exception;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(String message){
        super(message);
    }
}
