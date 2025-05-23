package com.javatraining.employee_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildSimpleErrorResponse(final Exception ex, final HttpStatus status) {
        return new ErrorResponse(status.value(), ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = buildSimpleErrorResponse(ex, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleResourceConflictException(ResourceConflictException ex){
        ErrorResponse errorResponse = buildSimpleErrorResponse(ex,HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
}
