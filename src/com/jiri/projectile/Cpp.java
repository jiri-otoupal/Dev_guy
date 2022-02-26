package com.jiri.projectile;

import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.*;

public class Cpp extends Projectile {
    public Cpp(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        super(currentLevel, damage, mass, enableGravity, applyPhysicsImpulse, spawnPoint, vector);
        representMap = new char[][]{{'C', '+', '+'}};
    }
}
