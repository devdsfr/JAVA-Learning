package com.example.cadastroveiculo.exception;

public class VeiculoAlreadyExistsException extends RuntimeException {
    public VeiculoAlreadyExistsException(String message) {
        super(message);
    }
}