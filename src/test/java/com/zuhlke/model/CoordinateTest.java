package com.zuhlke.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateTest {


    @Test
    void expectGetDistanceOf3and9() {
        // given
        Coordinate start = new Coordinate(2, 3);
        Coordinate end = new Coordinate(5, 12);

        // when
        Coordinate difference = end.getDistance(start);

        // then
        Coordinate expected = new Coordinate(5 - 2, 12 - 3);
        assertEquals(expected.getX(), difference.getX());
        assertEquals(expected.getY(), difference.getY());
    }

    @Test
    void expectStartLinearDistance5() {
        // given
        Coordinate start = new Coordinate(2, 3);

        // when
        int answer = start.linearDistance();

        // then
        int expected = start.getX() + start.getY();
        assertEquals(expected, answer);
    }

    @Test
    void expectLinearDistance10() {
        // given
        Coordinate point = new Coordinate(3, 7);

        // when
        int answer = point.linearDistance();

        // then
        int expected = 3 + 7;
        assertEquals(expected, answer);
    }


    @Test
    void expectPrintOutOfCoordinates() {
        // given
        Coordinate start = new Coordinate(2, 3);

        // when
        String answer = start.toString();

        // then
        String expected = "X-Coordinate: 2, Y-Coordinate: 3";
        assertEquals(expected, answer);
    }

    @Test
    void addOneToX() {
        // given
        Coordinate test = new Coordinate(3, 4);

        // when
        int answer = test.addX(-1);

        // then
        int expected = 3 - 1;
        assertEquals(expected, answer);

    }

    @Test
    void addOneToY() {
        // given
        Coordinate test = new Coordinate(3, 4);

        // when
        int answer = test.addY(-1);

        // then
        int expected = 4 - 1;
        assertEquals(expected, answer);
    }
}