package com.zuhlke.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CanvasTest {

    private Canvas canvas;
    private Character[][] testbase;
    String ans;
    String linebreak;

    @Before
    public void setUp() {
        canvas = new Canvas(10, 4);
        testbase = canvas.getBase();
        linebreak = System.getProperty("line.separator");
        ans = linebreak +
                "------------" + linebreak +
                "|          |" + linebreak +
                "|          |" + linebreak +
                "|          |" + linebreak +
                "|          |" + linebreak +
                "------------" + linebreak;
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