package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;
import org.apache.commons.lang3.StringUtils;

public class CreateCanvasCommand implements Command {

    @Override
    public Canvas execute(String[] input, Canvas source) {

        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        return new Canvas(x, y);
    }

    @Override
    public void validateInput(String[] input) throws InvalidInputException {

        if (input.length < 3) throw new InsufficientParametersException("Please input both width and height");

        for (int i = 1; i < input.length; i++) {
            // minimum canvas size is 1,1
            if (!StringUtils.isNumeric(input[i]) || Integer.parseInt(input[i]) <= 0) {
                throw new InvalidInputException("Width and height be numbers more than zero");
            }
        }

    }
}
