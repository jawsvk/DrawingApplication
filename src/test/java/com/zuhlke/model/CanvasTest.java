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
        // given
        Character ans = 'T';
        Coordinate coordinate = new Coordinate(1, 1);

        // when
        canvas.plot(coordinate, ans);

        // then
        assertEquals(ans, canvas.getCell(coordinate));
    }


    @Test
    void plotXExpectXatSamePoint() {
        // given
        Character ans = 'T';
        Coordinate coordinate = new Coordinate(2, 2);

        // when
        canvas.plot(coordinate, ans);

        // then
        assertEquals(ans, canvas.getCell(coordinate));
    }

    @Test
    void coordinateWithinCanvasReturnTrue() {
        // given
        Coordinate coordinate = new Coordinate(2, 2);

        // then
        assertTrue(canvas.isValidPoint(coordinate));
    }

    @Test
    void coordinateOutsideBoundaryReturnFalse() {
        // given
        Coordinate coordinate = new Coordinate(11, -2);

        // then
        assertFalse(canvas.isValidPoint(coordinate));
    }

    @Test
    void createCanvasDeepCopy() {
        // given
        Canvas testCanvas = new Canvas(canvas);

        // then
        assertNotEquals(testCanvas.toString(), canvas.toString());
    }

    @Test
    void checkCanvasCopyHasSameBase() {
        // given
        Canvas testCanvas;
        Coordinate point = new Coordinate(5, 5);

        // when
        canvas.plot(point, 'c');
        testCanvas = new Canvas(canvas);

        // then
        assertEquals(testCanvas.getCell(point), canvas.getCell(point));
    }

    @Test
    void expectCorrectPrintOut() {
        // given
        Canvas canvas = new Canvas(10, 4);

        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        // when
        canvas.print();

        // then
        String ans = br +
                "------------" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "------------" + br;

        assertEquals(ans, os.toString());
    }

}