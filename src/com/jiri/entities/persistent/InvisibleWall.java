package com.jiri.entities.persistent;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

import java.awt.*;

public class InvisibleWall extends Entity1D {

    public InvisibleWall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = '⠀';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
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
