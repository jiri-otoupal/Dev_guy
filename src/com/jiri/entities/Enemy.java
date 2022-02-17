package com.jiri.entities;

import com.jiri.level.Level;

public class Enemy extends AliveEntity {

    public Enemy(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, health, speed, fireRate, jumpHeight, gravity);
        this.frameDurationMs = 50;
        this.loops = true;
    }

    @Override
    public int resolveAnimationState() {
        if (falling) {
            return 1;
        } else if (moving) {
            return 2;
        } else if (shooting) {
            return 3;
        }
        //Idle State
        return 0;
    }

}
