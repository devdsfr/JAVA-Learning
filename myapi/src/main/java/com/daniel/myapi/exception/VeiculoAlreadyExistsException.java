package com.daniel.myapi.exception;

public class VeiculoAlreadyExistsException extends RuntimeException {
    public VeiculoAlreadyExistsException(String message) {
        super(message);
    }
}