package zuhlke.command;

import zuhlke.model.Canvas;
import zuhlke.model.Coordinate;

public class DrawLine implements Command {

    Coordinate startPt;
    Coordinate endPt;
    Canvas canvas;

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

    public void checkCoordinates(Coordinate start, Coordinate end, Canvas source) throws Exception {
        if (!source.isValidPoint(start) || !source.isValidPoint(end)) {
            throw new Exception("Invalid Parameters >> Either Start Point or End Point (or both) are out of bounds");
        }
    }

    public Canvas drawLine(Coordinate start, Coordinate end, Canvas source) {
        //returns difference between start and end in coordinate form
        Coordinate diff = end.getDistance(start);

        //loop through half of the linear distance
        for (int i = 0; i <= diff.linearDistance() / 2; i++) {
            source.plot(start, 'x');
            source.plot(end, 'x');

            //if else statements to move coordinates of end and start closer to each other
            if (diff.x > 0) {
                start.x++;
                end.x--;
            } else if (diff.x < 0) {
                end.x++;
                start.x--;
            }

            if (diff.y > 0) {
                start.y++;
                end.y--;
            } else if (diff.y < 0) {
                end.y++;
                start.y--;
            }

        }

        return source;
    }
}
