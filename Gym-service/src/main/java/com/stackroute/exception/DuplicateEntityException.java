package com.stackroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntityException extends RuntimeException {
	

    public DuplicateEntityException() {
        super("Duplicate Entry found");
    }

}
