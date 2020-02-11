package com.luisjn.backend.exception;

public class DatosNoValidosException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DatosNoValidosException(String message) {
        super(message);
    }
}
