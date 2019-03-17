package zuhlke.model;

public class Canvas {

    Character[][] base;
    int row;
    int column;

    public Canvas() {
    }

    public Canvas(int x, int y) {
        this.column = x + 2;
        this.row = y + 2;
        this.base = new Character[row][column];
        makeOutline();
    }

    public Character[][] getBase() {
        return base;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    //draw outline of the canvas
    void makeOutline() {
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
    public Character[][] plot(Coordinate point, Character x) {
        base[point.y][point.x] = x;
        return getBase();
    }

    //retrieve character at a single point in the canvas
    public Character getCell(Coordinate point) {

        return base[point.y][point.x];

    }

    //check if a coordinate lies within canvas
    public boolean isValidPoint(Coordinate point) {
        return (point.x >= 1 && point.x <= column && point.y >= 1 && point.y <= row);
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
