package com.neoris.portaltransaccional.exception;

public class BalanceLowerThanZeroException extends Exception{
    private String message;

    public BalanceLowerThanZeroException(String message) {
        super(message);
        this.message = message;
    }
    public BalanceLowerThanZeroException() {
    }
}
