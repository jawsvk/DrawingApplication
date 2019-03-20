package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;


public class DrawLine implements Command {

    private Coordinate startPt;
    private Coordinate endPt;
    private Canvas canvas;

    @Override
    public Canvas execute(String[] input, Canvas base) throws NoCanvasException {
        if (base == null) throw new NoCanvasException();
        canvas = base;

        startPt = new Coordinate(Integer.parseInt((input[1])), Integer.parseInt((input[2])));
        endPt = new Coordinate(Integer.parseInt((input[3])), Integer.parseInt((input[4])));
        Coordinate diff = endPt.getDistance(startPt);

        //check that coordinates are valid
        checkCoordinates(startPt, endPt, canvas);

        //check that end points form a vertical/horizontal line
        if (diff.getX() > 0 && diff.getY() > 0) {
            throw new InvalidInputException("Coordinates do not form a vertical or horizontal line");
        }

        drawLine(startPt, endPt, canvas);

        return canvas;
    }

    void checkCoordinates(Coordinate start, Coordinate end, Canvas source) throws InvalidInputException {

        //check that start point and end point is within the canvas
        if (!source.isValidPoint(start) || !source.isValidPoint(end)) {
            throw new InvalidInputException();
        }
    }

    Canvas drawLine(Coordinate start, Coordinate end, Canvas source) {

        canvas = source;
        //returns difference between start and end in coordinate form
        Coordinate diff = end.getDistance(start);

        //loop through half of the linear distance
        for (int i = 0; i <= diff.linearDistance() / 2; i++) {
            canvas.plot(start, 'x');
            canvas.plot(end, 'x');

            //if else statements to move coordinates of end and start closer to each other
            if (diff.getX() > 0) {
                start.addX(1);
                end.addX(-1);
            } else if (diff.getX() < 0) {
                end.addX(1);
                start.addX(-1);
            }

            if (diff.getY() > 0) {
                start.addY(1);
                end.addY(-1);
            } else if (diff.getY() < 0) {
                end.addY(1);
                start.addY(-1);
            }
        }
        return canvas;

    }
}
