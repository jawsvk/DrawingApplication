package zuhlke.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanvasTest {

    Canvas canvas;
    Character[][] testbase;

    @Before
    public void setUp() {
        canvas = new Canvas(10, 4);
        testbase = canvas.getBase();
    }

    @Test
    public void checkHorizontalOutline() {
        int count = 0;

        for (Character[] array : testbase) {
            for (Character c : array) {
                if (c == '-') count++;
            }
        }

        assertEquals(24, count);
    }

    @Test
    public void checkVerticalOutline() {
        int count = 0;

        for (Character[] array : testbase) {
            for (Character c : array) {
                if (c == '|') count++;
            }
        }

        assertEquals(8, count);

    }

    @Test
    public void plotXAtOnePoint() {
        Character ans = 'T';

        Coordinate coordinate = new Coordinate(1, 1);
        testbase = canvas.plot(coordinate, ans);

        assertEquals(ans, testbase[1][1]);
    }


    @Test
    public void plotXExpectXatSamePoint() {
        Character ans = 'T';
        Coordinate coordinate = new Coordinate(2, 2);
        testbase = canvas.plot(coordinate, ans);

        assertEquals(ans, canvas.getCell(coordinate));
    }

    @Test
    public void positiveCoordinateReturnTrue() {
        Coordinate coordinate = new Coordinate(2, 2);

        assertEquals(true, canvas.isValidPoint(coordinate));

    }

}