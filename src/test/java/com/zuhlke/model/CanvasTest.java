package com.zuhlke.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasTest {

    private Canvas canvas;
    private String ans;
    private String br;

    @Before
    public void setUp() {
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
    public void plotXAtOnePoint() {
        Character ans = 'T';

        Coordinate coordinate = new Coordinate(1, 1);
        canvas.plot(coordinate, ans);

        assertEquals(ans, canvas.getCell(coordinate));
    }


    @Test
    public void plotXExpectXatSamePoint() {
        Character ans = 'T';

        Coordinate coordinate = new Coordinate(2, 2);
        canvas.plot(coordinate, ans);

        assertEquals(ans, canvas.getCell(coordinate));
    }

    @Test
    public void positiveCoordinateReturnTrue() {
        Coordinate coordinate = new Coordinate(2, 2);

        assertTrue(canvas.isValidPoint(coordinate));

    }

    @Test
    public void ExpectCorrectPrintOut() {
        //redirect output
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        canvas.print();

        assertEquals(ans, os.toString());
    }
}