package com.zuhlke.model;

public class Canvas {

    private Character[][] base;
    private int row;
    private int column;

    public Canvas(int x, int y) {
        this.column = x + 2;
        this.row = y + 2;
        this.base = new Character[row][column];
        makeOutline();
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
        return (point.getX() >= 1 && point.getX() <= column && point.getY() >= 1 && point.getY() <= row);
    }

    public void print() {
        for (int i = 0; i < row; i++) {
            System.out.println();
            for (Character s : base[i]) {
                System.out.print(s);
            }
        }
        System.out.println();

    }
}
