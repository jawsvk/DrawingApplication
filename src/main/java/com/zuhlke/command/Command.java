package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

public interface Command {
    Canvas execute(String[] input, Canvas source) throws InvalidInputException;

    void validateInput(String[] input) throws InvalidInputException;

}
