package com.zuhlke.exception;

public class InvalidInputException extends IllegalArgumentException {

    public InvalidInputException() {
        super("Either Start Point or End Point (or both) are out of bounds");
    }

    public InvalidInputException(String s) {
        super(s);
    }
}
