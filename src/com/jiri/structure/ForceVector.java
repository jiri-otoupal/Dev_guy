package com.jiri.structure;

import java.awt.*;
import java.util.Objects;

public class ForceVector {
    public float x;
    public float y;

    public ForceVector(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    ForceVector(ForceVector forceVector) {
        this.x = forceVector.x;
        this.y = forceVector.y;
    }

    public ForceVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForceVector that = (ForceVector) o;
        return Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public ForceVector multiplyThis(ForceVector other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public ForceVector multiplyThis(float number) {
        this.x *= number;
        this.y *= number;
        return this;
    }

    public ForceVector multiply(float number) {
        ForceVector forceVectorCopy = new ForceVector(this);
        forceVectorCopy.x *= number;
        forceVectorCopy.y *= number;
        return forceVectorCopy;
    }

    public ForceVector negate() {
        ForceVector forceVectorCopy = new ForceVector(this);
        forceVectorCopy.x *= -1;
        forceVectorCopy.y *= -1;
        return forceVectorCopy;
    }

    public float computeVelocity() {
        return x + y;
    }
}
