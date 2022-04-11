package com.jiri.entities;

import com.jiri.entities.persistent.EmptySpace;
import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Movable extends Entity1D implements IMovable, IAnimation {
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


    /**
     * Normalizes Vector of Force to movement and applies movement
     *
     * @param deltaX
     * @param deltaY
     * @return
     */
    protected Point normalizeAndApplyDelta(float deltaX, float deltaY) {
        this.nowDeltaX += deltaX;
        this.nowDeltaY += deltaY;
        int deltaFlooredX = Math.round(this.nowDeltaX);
        int deltaFlooredY = Math.round(this.nowDeltaY);
        this.nowDeltaX -= deltaFlooredX;
        this.nowDeltaY -= deltaFlooredY;
        return new Point(deltaFlooredX, deltaFlooredY);
    }

    /**
     * Returns force vector added to current force vector of the object
     * without applying it
     *
     * @param deltaX delta x
     * @param deltaY delta y
     * @return normalized delta with this delta
     */
    protected Point normalizeDelta(float deltaX, float deltaY) {
        int deltaFlooredX = Math.round(this.nowDeltaX + deltaX);
        int deltaFlooredY = Math.round(this.nowDeltaY + deltaY);
        return new Point(deltaFlooredX, deltaFlooredY);
    }


    /**
     * Moves object relative to current movement vector
     *
     * @param deltaX
     * @param deltaY
     * @return
     */
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


    /**
     * Decays item by milliseconds
     *
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    abstract public void decayEffectFromItems(long ticksMs);


    /**
     * Adds movement to the movement list
     *
     * @param deltaX
     * @param deltaY
     */
    public void addMovement(float deltaX, float deltaY) {
        this.movements.add(new float[]{deltaX, deltaY});
    }


    /**
     * Apply Gravity
     */
    public void applyGravity() {
        move(0, this.gravity);
    }

    /**
     * Event called every tick if object subscribed to ticks
     *
     * @param elapsedMs between events
     */
    @Override
    public void tickEvent(long elapsedMs) {
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

    /**
     * Update Animation on event if subscribed
     * @param elapsedMs between updates
     */
    @Override
    abstract public void updateAnimation(long elapsedMs);


    /**
     * Resolve animation state to be rendered
     * such as jumping animation, shooting etc.
     *
     * @return current animation state to be animated
     */
    @Override
    public int resolveAnimationState() {
        return 0;
    }

    /**
     * Applies physics impulse that was set from different object
     *
     * @param mass   mass of object that hit
     * @param vector speed of object that hit in vector
     * @return True if Force Applied (can be overridden)
     */
    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        ForceVector vectorOfImpact = vector.multiply(mass / 10);
        addMovement(vectorOfImpact.x, vectorOfImpact.y);
        return true;
    }


    /**
     * Set animation state to be animated such as shooting, jumping etc.
     * @param state to be animated
     */
    @Override
    abstract public void setAnimationState(int state);

}
