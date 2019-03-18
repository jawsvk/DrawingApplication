package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

public class BucketFill implements Command {

    @Override
    public Canvas Execute(String[] input, Canvas canvas) {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (input[3].length() > 1) {
            throw new InvalidParameterException("New color can only be ONE character");
        }

        Character newColor = input[3].charAt(0);

        flood(origin, newColor, canvas);

        canvas.print();
        return canvas;
    }

    private void fillLeftRight(Coordinate origin, Character newColor, Canvas canvas, String ref) {

        Coordinate point = new Coordinate(origin.addX(1), origin.getY());
        Queue<Coordinate> queue = new LinkedList<>();

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(1), point.getY());

            if (ref.indexOf(canvas.getCell(point)) < 0) queue.add(point);
        }

        point = new Coordinate(origin.addX(-1), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(-1), point.getY());

            if (ref.indexOf(canvas.getCell(point)) < 0) queue.add(point);
        }

        // queue for replace recursion of vertical fill
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            fillUpDown(p, newColor, canvas, ref);
        }
    }

    private void fillUpDown(Coordinate origin, Character newColor, Canvas canvas, String ref) {
        Coordinate point = new Coordinate(origin.getX(), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            fillLeftRight(point, newColor, canvas, ref);
            point = new Coordinate(point.getX(), point.addY(1));
        }

        point = new Coordinate(origin.getX(), origin.addY(-1));

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            fillLeftRight(point, newColor, canvas, ref);
            point = new Coordinate(point.getX(), point.addY(-1));

        }
    }

    private void flood(Coordinate point, Character newColor, Canvas canvas) {

        Character oldColor = canvas.getCell(point);

        //Characters to avoid
        String ref = "-|";

        if (oldColor == 'x') {
            ref = ref + newColor + ' ';
        } else {
            ref = ref + newColor + 'x';
        }

        fillUpDown(point, newColor, canvas, ref);

    }

}
