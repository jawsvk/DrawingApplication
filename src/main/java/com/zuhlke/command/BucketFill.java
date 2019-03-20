package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

import java.util.LinkedList;
import java.util.Queue;

public class BucketFill implements Command {
    private Canvas canvas;
    private String ref;
    private Character targetColor;


    @Override
    public Canvas execute(String[] input, Canvas source) throws NoCanvasException, InvalidInputException {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (source == null) throw new NoCanvasException();
        canvas = source;

        if (input[3].length() > 1) {
            throw new InvalidInputException("New color can only be ONE character");
        }

        Character newColor = input[3].charAt(0);
        flood(origin, newColor);

        return canvas;
    }

    private Queue<Coordinate> checkUpDown(Queue<Coordinate> queue, Coordinate point) {
        //check up down
        Coordinate up = new Coordinate(point.getX(), point.getY() - 1);
        if (ref.indexOf(canvas.getCell(up)) < 0) queue.add(up);

        Coordinate down = new Coordinate(point.getX(), point.getY() + 1);
        if (ref.indexOf(canvas.getCell(down)) < 0) queue.add(down);

        return queue;
    }

    private Queue<Coordinate> checkLeftRight(Queue<Coordinate> queue, Coordinate point) {
        //check up down
        Coordinate left = new Coordinate(point.getX() - 1, point.getY());
        if (ref.indexOf(canvas.getCell(left)) < 0) queue.add(left);

        Coordinate right = new Coordinate(point.getX() + 1, point.getY());
        if (ref.indexOf(canvas.getCell(right)) < 0) queue.add(right);

        return queue;
    }

    private void fillLeftRight(Coordinate origin, Character newColor) {

        if (ref.indexOf(canvas.getCell(origin)) > 0 || canvas.getCell(origin) != targetColor) return;

        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate point = new Coordinate(origin.addX(1), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0 && canvas.getCell(point) == targetColor) {
            //check up down
            checkUpDown(queue, point);

            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(1), point.getY());
        }

        point = new Coordinate(origin.addX(-1), origin.getY());

        while (ref.indexOf(canvas.getCell(point)) < 0 && canvas.getCell(point) == targetColor) {
            //check up down
            checkUpDown(queue, point);

            //plot new color
            canvas.plot(point, newColor);
            point = new Coordinate(point.addX(-1), point.getY());
        }

        // queue for replace recursion of vertical fill
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            fillUpDown(p, newColor);
        }
    }

    private void fillUpDown(Coordinate origin, Character newColor) {

        if (ref.indexOf(canvas.getCell(origin)) > 0 || canvas.getCell(origin) != targetColor) return;

        Coordinate point = new Coordinate(origin.getX(), origin.getY());
        Queue<Coordinate> queue = new LinkedList<>();


        while (ref.indexOf(canvas.getCell(point)) < 0 && canvas.getCell(point) == targetColor) {
            //check left right
            checkLeftRight(queue, point);

            //plot new color
            canvas.plot(point, newColor);
            fillLeftRight(point, newColor);
            point = new Coordinate(point.getX(), point.addY(1));
        }

        point = new Coordinate(origin.getX(), origin.addY(-1));

        while (ref.indexOf(canvas.getCell(point)) < 0 && canvas.getCell(point) == targetColor) {
            //check left right
            checkLeftRight(queue, point);

            //plot new color
            canvas.plot(point, newColor);

            fillLeftRight(point, newColor);
            point = new Coordinate(point.getX(), point.addY(-1));
        }

        // queue for replace recursion of horizontal fill
        while (!queue.isEmpty()) {
            Coordinate p = queue.remove();
            fillLeftRight(p, newColor);
        }

    }

    private void flood(Coordinate point, Character newColor) throws InvalidInputException {

        ref = "-|";
        //Characters to avoid
        if (ref.contains(newColor.toString())) throw new InvalidInputException("Invalid color");
        else ref = ref + newColor;

        targetColor = canvas.getCell(point);

        fillUpDown(point, newColor);
    }

}
