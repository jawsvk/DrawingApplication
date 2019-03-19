package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

public class DrawRectangle extends DrawLine {

    private Coordinate corner1;
    private Coordinate corner2;
    private Coordinate corner3;
    private Coordinate corner4;

    @Override
    public Canvas Execute(String[] input, Canvas base) {
        corner1 = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        corner4 = new Coordinate(Integer.parseInt(input[3]), Integer.parseInt(input[4]));
        corner2 = new Coordinate(corner4.getX(), corner1.getY());
        corner3 = new Coordinate(corner1.getX(), corner4.getY());

        //make sure all the coordinates are valid
        checkCoordinates(corner1, corner4, base);

        //draw each side of the rectangle
        drawSide(corner1, corner2, base);
        drawSide(corner1, corner3, base);
        drawSide(corner3, corner4, base);
        drawSide(corner4, corner2, base);

        base.print();
        return base;
    }

    private void drawSide(Coordinate start, Coordinate end, Canvas canvas) {
        Coordinate x1 = new Coordinate(start.getX(), start.getY());
        Coordinate x2 = new Coordinate(end.getX(), end.getY());

        drawLine(x1, x2, canvas);

    }
}
