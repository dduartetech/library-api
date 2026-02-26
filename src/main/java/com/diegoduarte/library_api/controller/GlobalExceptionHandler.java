package com.diegoduarte.library_api.controller;

import com.diegoduarte.library_api.infrastructure.exceptions.ConflictException;
import com.diegoduarte.library_api.infrastructure.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handleConflictException(ConflictException ex) {
        String mensagem = (ex.getMessage() != null) ? ex.getMessage() : "Erro interno na operação";
        return new ResponseEntity<>(mensagem, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String mensagem = (ex.getMessage() != null) ? ex.getMessage() : "Erro interno na operação";
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }


}
