package com.zuhlke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {

    private Canvas canvas;
    private final String br = System.getProperty("line.separator");


    @BeforeEach
    void setUp() {
        canvas = new Canvas(10, 4);
    }

    @Test
    void plotXAtOnePoint() {
        Character ans = 'T';

        Coordinate coordinate = new Coordinate(1, 1);
        canvas.plot(coordinate, ans);

        assertEquals(ans, canvas.getCell(coordinate));
    }


    @Test
    void plotXExpectXatSamePoint() {
        Character ans = 'T';

        Coordinate coordinate = new Coordinate(2, 2);
        canvas.plot(coordinate, ans);

        assertEquals(ans, canvas.getCell(coordinate));
    }

    @Test
    void coordinateWithinCanvasReturnTrue() {
        Coordinate coordinate = new Coordinate(2, 2);
        assertTrue(canvas.isValidPoint(coordinate));
    }

    @Test
    void coordinateOutsideBoundaryReturnFalse() {
        Coordinate coordinate = new Coordinate(11, -2);
        assertFalse(canvas.isValidPoint(coordinate));
    }

    @Test
    void createCanvasDeepCopy() {
        Canvas testCanvas = new Canvas(canvas);

        assertNotEquals(testCanvas.toString(), canvas.toString());
    }

    @Test
    void checkCanvasCopyHasSameBase() {

        Coordinate point = new Coordinate(5, 5);
        canvas.plot(point, 'c');

        Canvas testCanvas = new Canvas(canvas);
        assertEquals(testCanvas.getCell(point), canvas.getCell(point));
    }

    @Test
    void expectCorrectPrintOut() {

        String ans = br +
                "------------" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "------------" + br;

        //redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        canvas.print();

        assertEquals(ans, os.toString());
    }

    @Test
    void drawHorizontalLineTest() {
        Coordinate start = new Coordinate(1, 2);
        Coordinate end = new Coordinate(6, 2);

        canvas.drawLine(start, end);

        int count = 0;
        for (Character c : canvas.getBase()[2]) {
            if (c == 'x') count++;
        }

        assertEquals(6, count);

    }
}