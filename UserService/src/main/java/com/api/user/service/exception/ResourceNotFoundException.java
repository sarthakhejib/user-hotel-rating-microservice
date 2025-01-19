package com.api.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(){
        super("Resource Not Found on the Server");
    }

    public ResourceNotFoundException(String message){
       super(message);
    }
}
