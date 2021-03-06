package com.jiri.entities.persistent;

import com.jiri.entities.Entity1D;
import com.jiri.entities.effects.EffectPersistentImpact;
import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.*;

public class Wall extends Entity1D {

    public Wall(Level currentLevel) {
        super(currentLevel);
        this.representMap = new char[1][1];
        this.representMap[0][0] = '█';
        this.persistent = true;
    }

    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
        this.currentLevel.streamer.assignAt(impactLocation, new EffectPersistentImpact(this.currentLevel, 50));
    }

    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        return false;
    }

    @Override
    public void applyDamage(float damage) {
    }

}
