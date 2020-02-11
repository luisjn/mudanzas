package com.luisjn.backend.controller.configuration;

import com.luisjn.backend.exception.DatosNoValidosException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ManejadorExcepciones extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DatosNoValidosException.class)
    public ResponseEntity<Object> handleDatosNoValidosException(DatosNoValidosException exception, WebRequest request) {
        HttpStatus requestedRangeNotSatisfiable = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE;
        String message = exception.getMessage();

        return handleExceptionInternal(exception, message, new HttpHeaders(), requestedRangeNotSatisfiable, request);
    }
}
