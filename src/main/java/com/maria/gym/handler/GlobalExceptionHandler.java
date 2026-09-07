package com.maria.gym.handler;

import com.maria.gym.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage(), webRequest.getDescription(false), LocalDate.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
