package com.jiri.entities;

import com.jiri.entities.persistent.EmptySpace;
import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Movable extends Entity1D implements IMovable, IAnimation {
    protected long timeToShoot = 0;
    public float health;
    public long fireRate;
    public float speed;
    public boolean enableGravity;
    public float gravity;
    protected boolean moving = false;
    protected float nowDeltaX = 0;
    protected float nowDeltaY = 0;
    public Queue<float[]> movements;
    protected List<IAnimation> animationListeners;
    boolean pushing = false;

    public Movable(Level currentLevel, boolean enableGravity) {
        super(currentLevel);
        this.enableGravity = enableGravity;
        if (!persistent && currentLevel.streamer != null) {
            currentLevel.streamer.addListener(this);
        }
        movements = new LinkedList<>();
        animationListeners = new LinkedList<>();
    }

    protected Point normalizeAndApplyDelta(float deltaX, float deltaY) {
        this.nowDeltaX += deltaX;
        this.nowDeltaY += deltaY;
        int deltaFlooredX = Math.round(this.nowDeltaX);
        int deltaFlooredY = Math.round(this.nowDeltaY);
        this.nowDeltaX -= deltaFlooredX;
        this.nowDeltaY -= deltaFlooredY;
        return new Point(deltaFlooredX, deltaFlooredY);
    }

    protected Point normalizeDelta(float deltaX, float deltaY) {
        int deltaFlooredX = Math.round(this.nowDeltaX + deltaX);
        int deltaFlooredY = Math.round(this.nowDeltaY + deltaY);
        return new Point(deltaFlooredX, deltaFlooredY);
    }


    public boolean move(float deltaX, float deltaY) {
        if (absPosition == null)
            return false;
        Point dl = normalizeDelta(deltaX, deltaY);
        if (this.isColliding().containsKey(dl)) {
            Entity1D collidingEntity = this.isColliding().get(dl);
            if (collidingEntity != null && !collidingEntity.persistent && !dl.equals(Directions.Bottom.vector) && !dl.equals(Directions.Top.vector)) {
                collidingEntity.shadow_parent.applyPhysicsImpulse(0.5F, new ForceVector(dl));
                this.pushing = true;
            }
            return false;
        }
        Point delta = normalizeAndApplyDelta(deltaX, deltaY);
        if (this.currentLevel.map[this.absPosition.y + delta.y][this.absPosition.x + delta.x].persistent)
            return false;
        this.currentLevel.map[this.absPosition.y][this.absPosition.x] = new EmptySpace(this.currentLevel);
        this.currentLevel.map[this.absPosition.y + delta.y][this.absPosition.x + delta.x] = this;
        this.absPosition = new Point(this.absPosition.x + delta.x, this.absPosition.y + delta.y);
        this.pushing = false;
        return true;
    }

    @Override
    public void decayEffectFromItems(long ticksMs) {

    }


    public void addMovement(float deltaX, float deltaY) {
        this.movements.add(new float[]{deltaX, deltaY});
    }


    public void applyGravity() {
        move(0, this.gravity);
    }

    @Override
    public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;
        this.timeToShoot -= correctedElapsed;
        if (currentLevel.streamer == null) {
            this.erase();
            return;
        }
        if (this.enableGravity && !this.persistent)
            applyGravity();
        if (!this.movements.isEmpty()) {
            float[] pts = this.movements.remove();
            if (move(pts[0], pts[1]))
                this.moving = true;
        } else {
            this.moving = false;
        }

        for (int i = 0; i < animationListeners.size(); i++) { // Needs to be used this type of iteration because of concurrent removal
            IAnimation listener = animationListeners.get(i);
            listener.updateAnimation(elapsedMs);
            listener.setAnimationState(resolveAnimationState());
        }
        decayEffectFromItems(elapsedMs);
    }

    @Override
    public void updateAnimation(long elapsedMs) {

    }

    @Override
    public int resolveAnimationState() {
        return 0;
    }

    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        ForceVector vectorOfImpact = vector.multiply(mass / 10);
        addMovement(vectorOfImpact.x, vectorOfImpact.y);
        return true;
    }

    @Override
    public void setAnimationState(int state) {
    }
}
