package com.jiri.entities;

import com.jiri.level.Level;

public class Player extends AliveEntity {
    public Player(Level currentLevel, int health, float speed, float fireRate) {
        super(currentLevel, health, speed, fireRate, 6, 0.2F);
        representMap = new char[][]{
                {' ', 'O', ' '},
                {'c', '|', 'c'},
                {' ', '*', ' '},
                {'/', ' ', '\\'}};
        this.currentLevel.levelStreamer.playerController.controlledPlayer = this;
    }

}
