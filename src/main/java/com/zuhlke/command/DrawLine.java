package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;


public class DrawLine implements Command {

    private Coordinate startPt;
    private Coordinate endPt;
    private Canvas canvas;

    @Override
    public Canvas Execute(String[] input, Canvas base) {
        startPt = new Coordinate(Integer.parseInt((input[1])), Integer.parseInt((input[2])));
        endPt = new Coordinate(Integer.parseInt((input[3])), Integer.parseInt((input[4])));
        Coordinate diff = endPt.getDistance(startPt);
        canvas = base;

        //check that coordinates are valid
        checkCoordinates(startPt, endPt, canvas);

        //check that end points form a vertical/horizontal line
        if (diff.getX() > 0 && diff.getY() > 0) {
            throw new IllegalArgumentException("Invalid Parameters >> Coordinates do not form a vertical or horizontal line");
        }

        drawLine(startPt, endPt, canvas);
        canvas.print();

        return canvas;
    }

    //package private method to be used in child class
     void checkCoordinates(Coordinate start, Coordinate end, Canvas source) {

        //check that start point and end point is within the canvas
        if (!source.isValidPoint(start) || !source.isValidPoint(end)) {
            throw new IllegalArgumentException("Invalid Parameters >> Either Start Point or End Point (or both) are out of bounds");
        }
    }

    //package private method to be used in child class
     Canvas drawLine(Coordinate start, Coordinate end, Canvas source) {
        //returns difference between start and end in coordinate form
        Coordinate diff = end.getDistance(start);

        //loop through half of the linear distance
        for (int i = 0; i <= diff.linearDistance() / 2; i++) {
            source.plot(start, 'x');
            source.plot(end, 'x');

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

        return source;
    }
}
