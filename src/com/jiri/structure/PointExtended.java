package com.jiri.structure;


import java.awt.Point;

public class PointExtended {

    public Point point;
    public PointExtended parent;
    public boolean requireJump;

    PointExtended(int x, int y) {
        this.point = new Point(x, y);
        this.parent = null;
    }


    public PointExtended(PointExtended point) {
        this.point = (Point) point.point.clone();
        this.parent = parent;
    }

    public PointExtended(Point point, PointExtended parent) {
        this.point = point;
        this.parent = parent;
    }

    public PointExtended(int x, int y, PointExtended parent) {
        this.point = new Point(x, y);
        this.parent = parent;
    }

    public PointExtended add(PointExtended b) {
        PointExtended copy = new PointExtended(this);
        copy.point.x += b.point.x;
        copy.point.y += b.point.y;
        return copy;
    }

    public PointExtended minus(PointExtended b) {
        PointExtended copy = new PointExtended(this);
        copy.point.x -= b.point.x;
        copy.point.y -= b.point.y;
        return copy;
    }

    public PointExtended multiply(float constant) {
        PointExtended copy = new PointExtended(this);
        copy.point.x *= constant;
        copy.point.y *= constant;
        return copy;
    }

    public int x() {
        return this.point.x;
    }

    public int y() {
        return point.y;
    }

    @Override
    public String toString() {
        return this.point.toString() + " " + this.requireJump;
    }
}
