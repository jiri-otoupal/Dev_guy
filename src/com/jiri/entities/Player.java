package com.jiri.entities;

import com.jiri.level.Level;

public class Player extends Movable {
    public Player(Level currentLevel, int health, float speed, float fireRate) {
        super(currentLevel, true);
        representMap = new char[][]{
                {' ', 'p', ' '},
                {'p', 'p', 'p'},
                {' ', 'p', ' '},
                {'p', ' ', 'p'}};
        this.health = 100;
        this.speed = speed;
        this.fireRate = fireRate;
        this.gravity = 0.5F;
        this.currentLevel.levelStreamer.playerController.controlledPlayer = this;
    }

    public void Jump() {
        if (!this.standingOnPersistent)
            return;
        for (int i = 0; i < 6; i++)
            addMovement(0F, -1F);

    }

    public void Crouch() {
    }

    public void MoveLeft() {
        addMovement(-1F, 0);
    }

    public void MoveRight() {
        addMovement(1F, 0);
    }

    public void Shoot() {
    }
}
