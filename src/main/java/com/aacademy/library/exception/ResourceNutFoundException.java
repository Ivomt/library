package com.aacademy.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNutFoundException extends RuntimeException{
    public ResourceNutFoundException(String message) {
        super(message);
    }
}
