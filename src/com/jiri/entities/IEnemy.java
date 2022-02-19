package com.jiri.entities;

public interface IEnemy {

    abstract public void attackIfClose(); // This is called automatically by Periodic search

    abstract public void moveOnPath();
}
