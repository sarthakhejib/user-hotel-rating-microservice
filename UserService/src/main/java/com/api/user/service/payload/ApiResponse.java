package com.api.user.service.payload;

import org.springframework.http.HttpStatus;

/**
 * A response class which will be used in GlobalExceptionHandler
 */
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiResponse(String message, boolean success, HttpStatus httpStatus) {
        this.message = message;
        this.success = success;
        this.httpStatus = httpStatus;
    }

}
