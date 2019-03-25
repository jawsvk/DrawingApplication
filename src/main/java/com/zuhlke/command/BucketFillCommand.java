package com.zuhlke.command;

import com.zuhlke.exception.InsufficientParametersException;
import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class BucketFillCommand implements Command {

    @Override
    public Canvas execute(String[] input, Canvas source) throws InvalidInputException {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (source == null) throw new NoCanvasException();
        Canvas canvas = new Canvas(source);

        input = validateInput(input);

        Character targetColor = canvas.getCell(origin);
        Character newColor = input[3].charAt(0);
        flood(origin, targetColor, newColor, canvas);

        return canvas;
    }

    private String[] validateInput(String[] input) throws InvalidInputException {
        //filter out double-spaces
        input = Arrays.stream(input).filter(s -> !s.isEmpty()).toArray(String[]::new);

        for (int i = 1; i < input.length - 1; i++) {
            if (!StringUtils.isNumeric(input[i]) || Integer.parseInt(input[i]) <= 0) {
                throw new InvalidInputException("Coordinates must be numbers more than zero");
            }
        }

        if (input.length < 4) throw new InsufficientParametersException();
        if (input[3].length() > 1) throw new InvalidInputException("Color should only have one character.");

        return input;
    }

    private void flood(Coordinate origin, Character targetColor, Character newColor, Canvas canvas) {

        if (targetColor == newColor) return;
        if (canvas.getCell(origin) != targetColor) return;

        canvas.plot(origin, newColor);

        LinkedList<Coordinate> queue = new LinkedList<>();
        queue.add(origin);

        while (!queue.isEmpty()) {
            Coordinate point = queue.removeFirst();

            //left
            Coordinate left = new Coordinate(point.getX() - 1, point.getY());
            if (canvas.getCell(left) == targetColor && canvas.isValidPoint(left)) {
                canvas.plot(left, newColor);
                queue.addLast(left);
            }

            //right
            Coordinate right = new Coordinate(point.getX() + 1, point.getY());
            if (canvas.getCell(right) == targetColor && canvas.isValidPoint(right)) {
                canvas.plot(right, newColor);
                queue.addLast(right);
            }

            //up
            Coordinate up = new Coordinate(point.getX(), point.getY() - 1);
            if (canvas.getCell(up) == targetColor && canvas.isValidPoint(up)) {
                canvas.plot(up, newColor);
                queue.addLast(up);
            }

            //down
            Coordinate down = new Coordinate(point.getX(), point.getY() + 1);
            if (canvas.getCell(down) == targetColor && canvas.isValidPoint(down)) {
                canvas.plot(down, newColor);
                queue.addLast(down);
            }
        }

    }
}
