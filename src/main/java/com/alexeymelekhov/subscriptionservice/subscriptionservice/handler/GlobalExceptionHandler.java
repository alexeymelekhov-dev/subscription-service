package com.alexeymelekhov.subscriptionservice.subscriptionservice.handler;

import com.alexeymelekhov.subscriptionservice.subscriptionservice.dto.ErrorResponseDTO;
import com.alexeymelekhov.subscriptionservice.subscriptionservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(ResourceNotFoundException e) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                new HashMap<>()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
