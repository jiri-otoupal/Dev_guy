package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;

public class Wall extends Entity1D {

    public Wall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = '#';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) {
        this.currentLevel.levelStreamer.assignAt(impactLocation, new EffectPersistentImpact(this.currentLevel, 50));
    }

    @Override
    public boolean applyPhysicsImpulse(float mass) {
        return false;
    }
    @Override
    public boolean applyDamage(float damage) {
        return false;
    }

}
