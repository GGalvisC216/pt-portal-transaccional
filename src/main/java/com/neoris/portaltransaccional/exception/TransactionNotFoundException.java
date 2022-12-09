package com.neoris.portaltransaccional.exception;

public class TransactionNotFoundException extends Exception{

    private String message;

    public TransactionNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public TransactionNotFoundException() {
    }

}
