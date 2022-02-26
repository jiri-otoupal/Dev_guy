package com.jiri.projectile;

import com.jiri.entities.Entity1D;
import com.jiri.entities.Movable;
import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

public class Projectile extends Movable {
    public ForceVector vector;
    public boolean appliesPhysicsImpulse;
    public float damage;
    public float mass;

    public Projectile(Level currentLevel, float damage, float mass, boolean enableGravity, boolean applyPhysicsImpulse, Point spawnPoint, ForceVector vector) {
        super(currentLevel, enableGravity);
        this.absPosition = spawnPoint;
        currentLevel.streamer.assignAt(spawnPoint, this);
        representMap = new char[][]{{'☕'}};
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
                if (!currentLevel.streamer.isValidLocation(absCollisionPos))
                    return;
                Entity1D usedInstance = this.currentLevel.streamer.getInstanceAt(absCollisionPos).shadow_parent;
                if (usedInstance == null)
                    continue;
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
