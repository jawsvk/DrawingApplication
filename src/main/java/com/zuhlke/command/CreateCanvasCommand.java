package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

public class CreateCanvasCommand implements Command {

    @Override
    public Canvas execute(String[] input, Canvas source) throws InvalidInputException {

        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        //minimum canvas size is 1,1
        if (x <= 0 || y <= 0) {
            throw new InvalidInputException("Width and Height must be more than zero");
        }

        return new Canvas(x, y);

    }
}
