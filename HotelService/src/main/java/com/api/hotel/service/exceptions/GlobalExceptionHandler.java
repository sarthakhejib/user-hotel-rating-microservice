package com.api.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException exception){
        Map map= new HashMap();
        map.put("message",exception.getMessage());
        map.put("success",false);
        map.put("status",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
