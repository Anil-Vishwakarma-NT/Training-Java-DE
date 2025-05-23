package com.javatraining.employee_management.dto;

public class MessageOutDto {

    private String message;

    public MessageOutDto(){

    }
    public MessageOutDto(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
