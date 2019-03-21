package com.zuhlke.exception;

public class InvalidInputException extends Exception {

    public InvalidInputException() {
        super("Either Start Point or End Point (or both) are out of bounds");

    }

    public InvalidInputException(String s) {
        super(s);

    }
}
