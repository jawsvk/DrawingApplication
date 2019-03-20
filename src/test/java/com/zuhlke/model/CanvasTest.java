package com.zuhlke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {

    private Canvas canvas;
    private String ans;
    private String br;

    @BeforeEach
    void setUp() {
        canvas = new Canvas(10, 4);
        br = System.getProperty("line.separator");
        ans = br +
                "------------" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "------------" + br;
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
    void CoordinateWithinCanvasReturnTrue() {
        Coordinate coordinate = new Coordinate(2, 2);
        assertTrue(canvas.isValidPoint(coordinate));
    }

    @Test
    void CoordinateOutsideBoundaryReturnFalse() {
        Coordinate coordinate = new Coordinate(11, -2);
        assertFalse(canvas.isValidPoint(coordinate));
    }

    @Test
    void ExpectCorrectPrintOut() {
        //redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        canvas.print();

        assertEquals(ans, os.toString());
    }
}