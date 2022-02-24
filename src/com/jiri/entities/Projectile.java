package com.jiri.entities;

import com.jiri.level.Level;
import com.jiri.structures.ForceVector;
import com.jiri.structures.PointExtended;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class Projectile extends Movable {
    public Point2D.Float vector;
    public boolean appliesPhysicsImpulse;
    public float damage;
    public float mass;

    public Projectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, Point2D.Float vector) {
        super(currentLevel, enableGravity);
        this.absPosition = spawnPoint;
        currentLevel.streamer.assignAt(spawnPoint, this);
        representMap = new char[][]{{'-'}};
        this.gravity = 0.003F;
        this.vector = vector;
        this.persistent = false;
        this.appliesPhysicsImpulse = applyPhysicsImpulse;
        this.damage = damage;
        this.mass = mass;
    }

    public void applyProjectileMovement() {
        if (vector == null || this.currentLevel.streamer == null)
            return;
        if (!move(this.vector.x, this.vector.y)) {
            erase(); // Destroy Instance
            for (Point collidingDelta : this.collisionDirections) {
                Point absCollisionPos = new Point(this.absPosition.x + (collidingDelta.x) * representMap[0].length, this.absPosition.y + collidingDelta.y);
                Entity1D usedInstance = this.currentLevel.streamer.getInstanceAt(absCollisionPos).shadow_parent;
                if (this.appliesPhysicsImpulse)
                    usedInstance.applyPhysicsImpulse(this.mass, new ForceVector(collidingDelta));
                usedInstance.applyDamage(this.damage);
                usedInstance.invokeImpactEffect(absPosition);
            }
        }
    }

    @Override
    public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        if (currentLevel.streamer == null) {
            this.erase();
            return;
        }
        if (this.enableGravity)
            applyGravity();
        applyProjectileMovement();
    }
}
