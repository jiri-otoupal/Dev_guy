package com.jiri.structure;

import java.awt.*;

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

}
