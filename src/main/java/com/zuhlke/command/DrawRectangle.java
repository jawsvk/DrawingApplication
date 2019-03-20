package com.zuhlke.command;

import com.zuhlke.exception.NoCanvasException;
import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

public class DrawRectangle extends DrawLine {

    private Canvas canvas;

    @Override
    public Canvas execute(String[] input, Canvas base) throws NoCanvasException {
        if (base == null) throw new NoCanvasException();
        canvas = base;
        Coordinate corner1 = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        Coordinate corner4 = new Coordinate(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        Coordinate corner2 = new Coordinate(corner4.getX(), corner1.getY());
        Coordinate corner3 = new Coordinate(corner1.getX(), corner4.getY());

        //make sure all the coordinates are valid
        checkCoordinates(corner1, corner4, canvas);

        //draw each side of the rectangle
        drawSide(corner1, corner2);
        drawSide(corner1, corner3);
        drawSide(corner3, corner4);
        drawSide(corner4, corner2);

        return canvas;
    }

    private void drawSide(Coordinate start, Coordinate end) {
        Coordinate x1 = new Coordinate(start.getX(), start.getY());
        Coordinate x2 = new Coordinate(end.getX(), end.getY());

        canvas = drawLine(x1, x2, canvas);

    }
}
