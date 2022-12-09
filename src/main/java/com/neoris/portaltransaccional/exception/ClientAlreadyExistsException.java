package com.neoris.portaltransaccional.exception;

public class ClientAlreadyExistsException extends Exception{

    private String message;

    public ClientAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
    public ClientAlreadyExistsException() {
    }

}
