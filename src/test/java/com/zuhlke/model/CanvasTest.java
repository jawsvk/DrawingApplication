package com.zuhlke.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Character expected = 'T';
        Coordinate coordinate = new Coordinate(1, 1);

        // when
        canvas.plot(coordinate, expected);

        // then
        assertEquals(expected, canvas.getCell(coordinate));
    }


    @Test
    void plotXExpectXatSamePoint() {
        // given
        Character expected = 'T';
        Coordinate coordinate = new Coordinate(2, 2);

        // when
        canvas.plot(coordinate, expected);

        // then
        assertEquals(expected, canvas.getCell(coordinate));
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
        assertNotEquals(testCanvas, canvas);
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
    void checkCanvasToString() {
        // given
        Canvas canvas = new Canvas(10, 4);

        // then
        String expected =
                "------------" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "|          |" + br +
                "------------" + br;

        assertEquals(expected, canvas.toString());
    }

    @Test
    void checkCanvasDrawLine() {
        // given
        Canvas canvas = new Canvas(10, 4);
        Coordinate start = new Coordinate(1, 2);
        Coordinate end = new Coordinate(3, 2);

        // when
        canvas.drawLine(start, end);

        // then
        String expected =
                "------------" + br +
                "|          |" + br +
                "|xxx       |" + br +
                "|          |" + br +
                "|          |" + br +
                "------------" + br;

        assertEquals(expected, canvas.toString());
    }

}