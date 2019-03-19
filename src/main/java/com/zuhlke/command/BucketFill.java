package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

public class BucketFill implements Command {
    Canvas canvas;


    @Override
    public Canvas Execute(String[] input, Canvas canvas) {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (input[3].length() > 1) {
            throw new InvalidParameterException("New color can only be ONE character");
        }

        this.canvas = canvas;
        Character newColor = input[3].charAt(0);
        flood(origin, newColor);

        canvas.print();
        return canvas;
    }

    private Queue<Coordinate> checkUpDown(Queue<Coordinate> queue, Coordinate point, String ref) {
        //check up down
        Coordinate up = new Coordinate(point.getX(), point.getY() - 1);
        if (ref.indexOf(canvas.getCell(up)) < 0) queue.add(up);

        Coordinate down = new Coordinate(point.getX(), point.getY() + 1);
        if (ref.indexOf(canvas.getCell(down)) < 0) queue.add(down);

        return queue;
    }

    private Queue<Coordinate> checkLeftRight(Queue<Coordinate> queue, Coordinate point, String ref) {
        //check up down
        Coordinate left = new Coordinate(point.getX()- 1, point.getY() );
        if (ref.indexOf(canvas.getCell(left)) < 0) queue.add(left);

        Coordinate right = new Coordinate(point.getX() + 1, point.getY());
        if (ref.indexOf(canvas.getCell(right)) < 0) queue.add(right);

        return queue;
    }

    private void fillLeftRight(Coordinate origin, Character newColor, String ref) {
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate point = new Coordinate(origin.addX(1), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //check up down
            queue = checkUpDown(queue, point, ref);

            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(1), point.getY());
        }

        point = new Coordinate(origin.addX(-1), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //check up down
            queue = checkUpDown(queue, point, ref);

            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(-1), point.getY());
        }

        // queue for replace recursion of vertical fill
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            fillUpDown(p, newColor, ref);
        }
    }

    private void fillUpDown(Coordinate origin, Character newColor, String ref) {
        Coordinate point = new Coordinate(origin.getX(), origin.getY());
        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate left, right;

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //check left right
            queue = checkLeftRight(queue,point,ref);

            //plot new color
            canvas.plot(point, newColor);
            fillLeftRight(point, newColor, ref);
            point = new Coordinate(point.getX(), point.addY(1));
        }

        point = new Coordinate(origin.getX(), origin.addY(-1));

        while (ref.indexOf(canvas.getCell(point)) < 0) {
            //check left right
            queue = checkLeftRight(queue,point,ref);

            //plot new color
            canvas.plot(point, newColor);

            fillLeftRight(point, newColor, ref);
            point = new Coordinate(point.getX(), point.addY(-1));
        }

        // queue for replace recursion of horizontal fill
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            fillLeftRight(p, newColor, ref);
        }

    }

    private void flood(Coordinate point, Character newColor) {
        Character oldColor = canvas.getCell(point);

        //Characters to avoid
        String ref = "-|";

        if (oldColor == 'x') {
            ref = ref + newColor + ' ';
        } else {
            ref = ref + newColor + 'x';
        }

        fillUpDown(point, newColor, ref);
    }

}
