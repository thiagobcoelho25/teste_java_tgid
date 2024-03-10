package com.example.teste_backend_java_tgid.exceptions.handlers;


import com.example.teste_backend_java_tgid.exceptions.BusinessRuleException;
import com.example.teste_backend_java_tgid.exceptions.ResponseStandardError;
import com.example.teste_backend_java_tgid.exceptions.ResponseValidationError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerErrors {

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ResponseStandardError> businessRule(BusinessRuleException e, HttpServletRequest request){
        ResponseStandardError err = new ResponseStandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, request.getRequestURI(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseStandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        ResponseStandardError err = new ResponseStandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND,request.getRequestURI(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseValidationError> handleValidationExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> {String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ResponseValidationError err = new ResponseValidationError(System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST, request.getRequestURI(), "Requisição possui Campo(s) Inválido(s)", errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


}
