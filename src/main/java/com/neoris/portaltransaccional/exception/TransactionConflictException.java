package com.neoris.portaltransaccional.exception;

public class TransactionConflictException extends Exception{
    private String message;

    public TransactionConflictException(String message) {
        super(message);
        this.message = message;
    }
    public TransactionConflictException() {
    }
}
