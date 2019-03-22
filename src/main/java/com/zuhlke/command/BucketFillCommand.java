package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

import java.util.LinkedList;

public class BucketFillCommand implements Command {

    @Override
    public Canvas execute(String[] input, Canvas source) throws InvalidInputException {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (source == null) throw new NoCanvasException();
        Canvas canvas = new Canvas(source);

        if (input[3].length() > 1 || "-|".contains(input[3])) {
            throw new InvalidInputException("Invalid color.");
        }

        Character targetColor = canvas.getCell(origin);
        Character newColor = input[3].charAt(0);
        flood(origin, targetColor, newColor, canvas);

        return canvas;
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
            if (canvas.getCell(left) == targetColor) {
                canvas.plot(left, newColor);
                queue.addLast(left);
            }

            //right
            Coordinate right = new Coordinate(point.getX() + 1, point.getY());
            if (canvas.getCell(right) == targetColor) {
                canvas.plot(right, newColor);
                queue.addLast(right);
            }

            //up
            Coordinate up = new Coordinate(point.getX(), point.getY() - 1);
            if (canvas.getCell(up) == targetColor) {
                canvas.plot(up, newColor);
                queue.addLast(up);
            }

            //down
            Coordinate down = new Coordinate(point.getX(), point.getY() + 1);
            if (canvas.getCell(down) == targetColor) {
                canvas.plot(down, newColor);
                queue.addLast(down);
            }
        }

    }
}
