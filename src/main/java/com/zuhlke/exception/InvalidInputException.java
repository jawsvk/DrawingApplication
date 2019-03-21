package com.zuhlke.exception;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 2L;

    public InvalidInputException() {
        super("Either Start Point or End Point (or both) are out of bounds");

    }

    public InvalidInputException(String s) {
        super(s);

    }
}
