package com.project.receiverdomain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSalesEntryFoundException extends Exception {

    public NoSalesEntryFoundException() {
    }

    public NoSalesEntryFoundException(String message) {
        super(message);
    }
}
