package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.apache.commons.lang3.StringUtils;

public abstract class Command {

    static boolean checkInputCoordinates(String[] input) {
        for (int i = 1; i < input.length; i++) {
            // minimum canvas size is 1,1
            if (!StringUtils.isNumeric(input[i]) || Integer.parseInt(input[i]) <= 0) {
                return true;
            }
        }
        return false;
    }

    static boolean checkInputLength(String[] input, int required) {
        return input.length < required;
    }

    public abstract Canvas execute(String[] input, Canvas source) throws InvalidInputException;

    public abstract void validateInput(String[] input) throws InvalidInputException;

}
