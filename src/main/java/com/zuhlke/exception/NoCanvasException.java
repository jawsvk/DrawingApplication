package com.zuhlke.exception;

public class NoCanvasException extends InvalidInputException {

    private static final long serialVersionUID = 1L;

    public NoCanvasException() {
        super("No canvas found. Please create a canvas with Command C");
    }


}
