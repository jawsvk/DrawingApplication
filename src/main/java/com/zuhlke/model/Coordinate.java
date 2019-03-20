package com.zuhlke.model;

public final class Coordinate {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int addX(int i) {
        return this.x = this.x + i;
    }

    public int addY(int i) {
        return this.y = this.y + i;
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
    public String toString() {
        return String.format("X-Coordinate: %s, Y-Coordinate: %s", this.x, this.y);
    }


}
