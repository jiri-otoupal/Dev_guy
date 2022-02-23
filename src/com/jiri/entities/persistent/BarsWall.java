package com.jiri.entities.persistent;

import com.jiri.entities.Entity1D;
import com.jiri.entities.effects.EffectPersistentImpact;
import com.jiri.level.Level;

import java.awt.*;

public class BarsWall extends Entity1D {
    public BarsWall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = 'ꔖ';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) {
        this.currentLevel.streamer.assignAt(impactLocation, new EffectPersistentImpact(this.currentLevel, 50));
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
