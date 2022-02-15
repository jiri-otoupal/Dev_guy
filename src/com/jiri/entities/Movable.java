package com.jiri.entities;

public class Movable extends Entity implements IMovable {
    public float health;
    public float fireRate;
    public float speed;

    public boolean move() {
        return false;
    }
}
