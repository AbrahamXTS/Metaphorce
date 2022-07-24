package com.metaphorce.rrhh.config;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.metaphorce.rrhh.utilities.WrapperResponse;
import com.metaphorce.rrhh.exceptions.AlreadyExistException;
import com.metaphorce.rrhh.exceptions.NonExistException;
import com.metaphorce.rrhh.exceptions.ValidatePropertieException;

@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> GeneralErrorHandler(Exception e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false, "Ops! Something went wrong: " + e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> AlreadyExistExceptionHandler(Exception e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonExistException.class)
    public ResponseEntity<?> NonExistExceptionHandler(Exception e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ValidatePropertieException.class)
    public ResponseEntity<?> ValidatePropertieExceptionHandler(Exception e, WebRequest request) {
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
