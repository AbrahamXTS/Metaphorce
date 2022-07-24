package com.metaphorce.rrhh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidatePropertieException extends RuntimeException {

    public ValidatePropertieException() {}

    public ValidatePropertieException(String message) {
        super(message);
    }

    public ValidatePropertieException(Throwable cause) {
        super(cause);
    }

    public ValidatePropertieException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatePropertieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
