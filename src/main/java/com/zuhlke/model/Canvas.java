package com.zuhlke.model;

import java.util.Arrays;

public class Canvas {

    private final Character[][] base;
    private final int row;
    private final int column;

    //copy constructor
    public Canvas(Canvas canvas) {
        this.row = canvas.getRow();
        this.column = canvas.getColumn();
        this.base = new Character[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                base[i][j] = canvas.getBase()[i][j];
            }
        }
    }

    private int getRow() {
        return row;
    }

    public Canvas(int x, int y) {
        this.column = x + 2;
        this.row = y + 2;
        this.base = new Character[row][column];
        makeOutline();
    }

    private int getColumn() {
        return column;
    }

    Character[][] getBase() {
        return base;
    }

    //draw outline of the canvas
    private void makeOutline() {
        //loop to draw horizontal borders
        for (int i = 0; i < base[0].length; i++) {
            base[0][i] = '-';
            base[base.length - 1][i] = '-';

        }
        //loop to draw vertical borders
        for (int i = 1; i < base.length - 1; i++) {
            base[i][0] = '|';
            base[i][base[0].length - 1] = '|';
        }

        //loop to fill empty spaces
        for (int i = 1; i < base.length - 1; i++) {
            for (int j = 1; j < base[0].length - 1; j++) {
                base[i][j] = ' ';
            }

        }
    }

    //plot a single point in the canvas
    public void plot(Coordinate point, Character x) {
        base[point.getY()][point.getX()] = x;
    }

    //retrieve character at a single point in the canvas
    public Character getCell(Coordinate point) {
        return base[point.getY()][point.getX()];

    }

    //check if a coordinate lies within canvas
    public boolean isValidPoint(Coordinate point) {
        return (point.getX() >= 1 && point.getX() < column - 1 && point.getY() >= 1 && point.getY() < row - 1);
    }

    public void print() {
        for (int i = 0; i < row; i++) {
            System.out.println();
            Arrays.stream(base[i]).forEach(System.out::print);
        }
        System.out.println();

    }

    public Canvas drawLine(Coordinate start, Coordinate end) {
        Coordinate diff = end.getDistance(start);

        //loop through half of the linear distance
        for (int i = 0; i <= diff.linearDistance() / 2; i++) {
            this.plot(start, 'x');
            this.plot(end, 'x');

            //if else statements to move coordinates of end and start closer to each other
            if (diff.getX() > 0) {
                start.addX(1);
                end.addX(-1);
            } else if (diff.getX() < 0) {
                end.addX(1);
                start.addX(-1);
            }

            if (diff.getY() > 0) {
                start.addY(1);
                end.addY(-1);
            } else if (diff.getY() < 0) {
                end.addY(1);
                start.addY(-1);
            }
        }
        return this;
    }
}
