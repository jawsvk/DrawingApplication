package com.zuhlke.command;

import com.zuhlke.exception.InvalidInputException;
import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

public class DrawRectangleCommand extends DrawLineCommand {

    @Override
    public Canvas execute(String[] input, Canvas source) throws InvalidInputException {
        if (source == null) throw new NoCanvasException();
        Canvas canvas = new Canvas(source);

        Coordinate corner1 = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        Coordinate corner4 = new Coordinate(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        Coordinate corner2 = new Coordinate(corner4.getX(), corner1.getY());
        Coordinate corner3 = new Coordinate(corner1.getX(), corner4.getY());

        //make sure all the coordinates are valid
        checkCoordinates(corner1, corner4, canvas);

        //draw each side of the rectangle
        canvas = drawSide(corner1, corner2, canvas);
        canvas = drawSide(corner1, corner3, canvas);
        canvas = drawSide(corner3, corner4, canvas);
        canvas = drawSide(corner4, corner2, canvas);

        return canvas;
    }

    private Canvas drawSide(Coordinate start, Coordinate end, Canvas source) {

        Canvas canvas = new Canvas(source);
        Coordinate x1 = new Coordinate(start.getX(), start.getY());
        Coordinate x2 = new Coordinate(end.getX(), end.getY());

        return canvas.drawLine(x1, x2);
    }
}
