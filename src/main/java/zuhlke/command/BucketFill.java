package zuhlke.command;

import zuhlke.model.Canvas;
import zuhlke.model.Coordinate;

import java.security.InvalidParameterException;

public class BucketFill implements Command {

    @Override
    public Canvas Execute(String[] input, Canvas canvas) throws Exception {
        Coordinate origin = new Coordinate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));

        if (input[3].length() > 1) {
            throw new InvalidParameterException("New color can only be ONE character");
        }

        Character newColor = input[3].charAt(0);

        fill(origin, newColor, canvas);

        canvas.print();
        return canvas;
    }

    void fill(Coordinate origin, Character newColor, Canvas canvas) {
        Character oldColor = canvas.getCell(origin);
        flood(origin, newColor, oldColor, canvas);
    }

    void flood(Coordinate point, Character newColor, Character oldColor, Canvas canvas) {
        //Characters to avoid
        String ref = "-|";

        if (oldColor == 'x') {
            ref = ref + newColor + ' ';
        } else {
            ref = ref + newColor + 'x';
        }

        if (ref.indexOf(canvas.getCell(point)) >= 0) {
            return;
        }

        //plot new color
        canvas.plot(point, newColor);

        flood(new Coordinate(point.x + 1, point.y), newColor, oldColor, canvas);
        flood(new Coordinate(point.x, point.y + 1), newColor, oldColor, canvas);
        flood(new Coordinate(point.x - 1, point.y), newColor, oldColor, canvas);
        flood(new Coordinate(point.x, point.y - 1), newColor, oldColor, canvas);
    }

}
