package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;

public class Projectile extends Movable {
    public Point2D.Float vector;
    public boolean appliesPhysicsImpulse;
    public float damage;
    public float mass;

    public Projectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, Point2D.Float vector) {
        super(currentLevel, enableGravity);
        this.absPosition = spawnPoint;
        currentLevel.levelStreamer.assignAt(spawnPoint, this);
        representMap = new char[][]{{'-'}};
        this.gravity = 0.003F;
        this.vector = vector;
        this.persistent = false;
        this.appliesPhysicsImpulse = applyPhysicsImpulse;
        this.damage = damage;
        this.mass = mass;
    }

    public void applyProjectileMovement() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        if (!move(this.vector.x, this.vector.y)) {
            erase(); // Destroy Instance
            for (Point collidingDelta : this.collisionDirections) {
                Point absCollisionPos = new Point(this.absPosition.x + (collidingDelta.x) * representMap[0].length, this.absPosition.y + collidingDelta.y);
                Entity1D usedInstance = this.currentLevel.levelStreamer.getInstanceAt(absCollisionPos).shadow_parent;
                if (this.appliesPhysicsImpulse)
                    usedInstance.applyPhysicsImpulse(this.mass);
                usedInstance.applyDamage(this.damage);
                usedInstance.invokeImpactEffect(absPosition);
            }
        }
    }

    @Override
    public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        if (this.enableGravity)
            applyGravity();
        applyProjectileMovement();
    }
}
