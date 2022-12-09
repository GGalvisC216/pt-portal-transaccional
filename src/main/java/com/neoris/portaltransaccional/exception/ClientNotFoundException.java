package com.neoris.portaltransaccional.exception;

public class ClientNotFoundException extends Exception{

    private String message;

    public ClientNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public ClientNotFoundException() {
    }

}
