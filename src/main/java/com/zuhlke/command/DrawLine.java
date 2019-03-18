package com.zuhlke.command;

import com.zuhlke.model.Canvas;
import com.zuhlke.model.Coordinate;

import java.security.InvalidParameterException;

public class DrawLine implements Command {

    private Coordinate startPt;
    private Coordinate endPt;
    private Canvas canvas;

    @Override
    public Canvas Execute(String[] input, Canvas base) throws Exception {
        startPt = new Coordinate(Integer.parseInt((input[1])), Integer.parseInt((input[2])));
        endPt = new Coordinate(Integer.parseInt((input[3])), Integer.parseInt((input[4])));
        canvas = base;

        //check that coordinates are valid
        checkCoordinates(startPt, endPt, canvas);

        drawLine(startPt, endPt, canvas);

        canvas.print();

        return canvas;
    }

    protected void checkCoordinates(Coordinate start, Coordinate end, Canvas source) {
        if (!source.isValidPoint(start) || !source.isValidPoint(end)) {
            throw new InvalidParameterException("Invalid Parameters >> Either Start Point or End Point (or both) are out of bounds");
        }
    }

    protected Canvas drawLine(Coordinate start, Coordinate end, Canvas source) {
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
