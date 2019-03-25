package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;


public class DrawLineCommand implements Command {

    @Override
    public Canvas execute(String[] input, Canvas source) throws InvalidInputException {
        if (source == null) throw new NoCanvasException();

        Canvas canvas = new Canvas(source);

        Coordinate startPt = new Coordinate(Integer.parseInt((input[1])), Integer.parseInt((input[2])));
        Coordinate endPt = new Coordinate(Integer.parseInt((input[3])), Integer.parseInt((input[4])));
        Coordinate diff = endPt.getDistance(startPt);

        //check that coordinates are valid
        checkCoordinates(startPt, endPt, canvas);

        //check that end points form a vertical/horizontal line
        if (diff.getX() > 0 && diff.getY() > 0) {
            throw new InvalidInputException("Coordinates do not form a vertical or horizontal line");
        }

        canvas.drawLine(startPt, endPt);

        return canvas;
    }

    void checkCoordinates(Coordinate start, Coordinate end, Canvas source) throws InvalidInputException {

        //check that start point and end point is within the canvas
        if (!source.isValidPoint(start) || !source.isValidPoint(end)) {
            throw new InvalidInputException();
        }
    }

}
