package com.jiri.entities.enemies;

public interface IEnemy {


    /**
     * Does attack or shoots if player is in scope
     */
    void attackIfClose(); // This is called automatically by Periodic search

    /**
     * Move on path that was discovered
     */
    void moveOnPath();
}
