package com.project.receiverdomain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SalesEntryAlreadyExistsException extends Exception {

    public SalesEntryAlreadyExistsException() {
    }

    public SalesEntryAlreadyExistsException(String message) {
        super(message);
    }
}
