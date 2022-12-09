package com.neoris.portaltransaccional.exception;

public class AccountNotFoundException extends Exception{

    private String message;

    public AccountNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public AccountNotFoundException() {
    }

}
