package com.zuhlke.exception;

public class InsufficientParametersException extends InvalidInputException {

    private static final long serialVersionUID = 3L;

    public InsufficientParametersException() {
        super("Insufficient parameters to carry out command.");

    }

    public InsufficientParametersException(String s) {
        super(s);

    }
}
