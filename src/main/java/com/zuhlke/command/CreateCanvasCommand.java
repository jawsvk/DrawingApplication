package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.model.Canvas;

public class CreateCanvasCommand extends Command {
    private final static int INPUT_LENGTH = 3;

    @Override
    public Canvas execute(String[] input, Canvas source) {

        int x = Integer.parseInt(input[1]);
        int y = Integer.parseInt(input[2]);

        return new Canvas(x, y);
    }

    @Override
    public void validateInput(String[] input) throws InvalidInputException {

        if (checkInputLength(input, INPUT_LENGTH))
            throw new InsufficientParametersException("Please input both width and height");

        if (checkInputCoordinates(input)) {
            throw new InvalidInputException("Width and height be numbers more than zero");
        }
    }

}
