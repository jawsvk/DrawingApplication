package com.zuhlke.exception;

public class NoCanvasException extends InvalidInputException {

    public NoCanvasException() {
        super("No canvas found. Please create a canvas with Command C");
    }


}
