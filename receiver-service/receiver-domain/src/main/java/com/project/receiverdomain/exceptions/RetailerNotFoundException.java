package com.project.receiverdomain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RetailerNotFoundException extends Exception {

    public RetailerNotFoundException() {
    }

    public RetailerNotFoundException(String message) {
        super(message);
    }
}
