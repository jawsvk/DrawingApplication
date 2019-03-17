package zuhlke.model;

public class Coordinate {

    public int x;
    public int y;

    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getDistance(Coordinate ref) {
        return new Coordinate(this.x - ref.x, this.y - ref.y);
    }

    public int linearDistance() {
        return Math.abs(this.x) + Math.abs(this.y);
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate ref = (Coordinate) obj;
        return (x == ref.x && y == ref.y);
    }

    public void print() {
        System.out.println(String.format("X-Coordinate: %s, Y-Coordinate: %s", this.x, this.y));
    }

}
