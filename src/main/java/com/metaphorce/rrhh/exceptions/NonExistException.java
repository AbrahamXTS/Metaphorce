package com.metaphorce.rrhh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NonExistException extends RuntimeException {

    public NonExistException() {}

    public NonExistException(String message) {
        super(message);
    }
}
