package com.zuhlke.model;

public class Canvas {

    private final Character[][] base;
    private final int row;
    private final int column;

    // deep copy constructor
    public Canvas(Canvas canvas) {
        this.row = canvas.getRow();
        this.column = canvas.getColumn();
        this.base = new Character[row + 2][column + 2];

        for (int i = 0; i < row + 2; i++) {
            System.arraycopy(canvas.getBase()[i], 0, base[i], 0, column + 2);
        }
    }

    private int getRow() {
        return row;
    }

    private int getColumn() {
        return column;
    }

    public Canvas(int x, int y) {
        this.column = x;
        this.row = y;
        this.base = new Character[row + 2][column + 2];
        makeOutline();
    }

    private Character[][] getBase() {
        return base;
    }

    // draw outline of the canvas
    private void makeOutline() {
        // loop to draw horizontal borders
        for (int i = 0; i < base[0].length; i++) {
            base[0][i] = '-';
            base[base.length - 1][i] = '-';

        }
        // loop to draw vertical borders
        for (int i = 1; i < base.length - 1; i++) {
            base[i][0] = '|';
            base[i][base[0].length - 1] = '|';
        }

        // loop to fill empty spaces
        for (int i = 1; i < base.length - 1; i++) {
            for (int j = 1; j < base[0].length - 1; j++) {
                base[i][j] = ' ';
            }
        }
    }

    // plot a single point in the canvas
    public void plot(Coordinate point, Character x) {
        base[point.getY()][point.getX()] = x;
    }

    // retrieve character at a single point in the canvas
    public Character getCell(Coordinate point) {
        return base[point.getY()][point.getX()];
    }

    // check if a coordinate lies within canvas
    public boolean isValidPoint(Coordinate point) {
        return (point.getX() >= 1 && point.getX() <= column && point.getY() >= 1 && point.getY() <= row);
    }

    public void print() {
        for (int i = 0; i < row + 2; i++) {
            System.out.println();
            for (Character c : base[i]) System.out.print(c);
        }
        System.out.println();
    }

    public void drawLine(Coordinate start, Coordinate end) {

        setValidCoordinate(end);
        setValidCoordinate(start);
        Coordinate diff = end.getDistance(start);
        Coordinate point;

        // assign point to the smaller coordinate
        if (diff.getY() > 0 || diff.getX() > 0) {
            point = new Coordinate(start.getX(), start.getY());
        } else {
            point = new Coordinate(end.getX(), end.getY());
        }

        // loop through linear distance
        for (int i = 0; i <= diff.linearDistance(); i++) {
            this.plot(point, 'x');

            if (diff.getX() == 0) point.addY(1);
            if (diff.getY() == 0) point.addX(1);
        }

    }

    private void setValidCoordinate(Coordinate point) {
        point.setX(point.getX() < 1 ? 1 : point.getX());
        point.setY(point.getY() < 1 ? 1 : point.getY());
        point.setX(point.getX() > column ? column : point.getX());
        point.setY(point.getY() > row ? row : point.getY());
    }
}
