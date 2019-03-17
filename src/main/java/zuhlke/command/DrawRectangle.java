package zuhlke.command;

import zuhlke.model.Canvas;
import zuhlke.model.Coordinate;

public class DrawRectangle extends DrawLine {

    Coordinate corner1;
    Coordinate corner2;
    Coordinate corner3;
    Coordinate corner4;

    @Override
    public Canvas Execute(String[] input, Canvas base) throws Exception {
        corner1 = new Coordinate(Integer.parseInt(input[1]),Integer.parseInt(input[2]));
        corner4 = new Coordinate(Integer.parseInt(input[3]),Integer.parseInt(input[4]));
        corner2 = new Coordinate(corner4.x, corner1.y);
        corner3 = new Coordinate(corner1.x,corner4.y);

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

    void drawSide(Coordinate start, Coordinate end, Canvas canvas) {
        Coordinate x1 = new Coordinate(start.x,start.y);
        Coordinate x2 = new Coordinate(end.x,end.y);

        drawLine(x1,x2, canvas);

    }
}
