package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Movable extends Entity1D implements IMovable {
    public float health;
    public float fireRate;
    public float speed;
    public boolean enableGravity;
    public float gravity;
    protected float nowDeltaX = 0;
    protected float nowDeltaY = 0;
    protected Thread moveInterpolateThread;
    protected Queue<float[]> movements;

    public Movable(Level currentLevel, boolean enableGravity) {
        super(currentLevel);
        this.enableGravity = enableGravity;
        if (!persistent && currentLevel.levelStreamer != null) {
            currentLevel.levelStreamer.addListener(this);
        }
        movements = new LinkedList<float[]>();
    }

    protected Point normalizeAndApplyDelta(float deltaX, float deltaY) {
        this.nowDeltaX += deltaX;
        this.nowDeltaY += deltaY;
        int deltaFlooredX = (int) Math.round(this.nowDeltaX);
        int deltaFlooredY = (int) Math.round(this.nowDeltaY);
        this.nowDeltaX -= deltaFlooredX;
        this.nowDeltaY -= deltaFlooredY;
        return new Point(deltaFlooredX, deltaFlooredY);
    }

    protected Point normalizeDelta(float deltaX, float deltaY) {
        int deltaFlooredX = (int) Math.round(this.nowDeltaX + deltaX);
        int deltaFlooredY = (int) Math.round(this.nowDeltaY + deltaY);
        return new Point(deltaFlooredX, deltaFlooredY);
    }

    public boolean move(float deltaX, float deltaY) {
        //TODO: move obj in map with abs position
        Point dl = normalizeDelta(deltaX, deltaY);
        if (this.isColliding().contains(dl))
            return false;
        Point delta = normalizeAndApplyDelta(deltaX, deltaY);
        this.currentLevel.map[this.absPosition.y][this.absPosition.x] = new EmptySpace(this.currentLevel);
        this.currentLevel.map[this.absPosition.y + delta.y][this.absPosition.x + delta.x] = this;
        this.absPosition = new Point(this.absPosition.x + delta.x,this.absPosition.y + delta.y);
        return true;
    }

    public void addMovement(float deltaX, float deltaY) {
        this.movements.add(new float[]{deltaX, deltaY});
    }

    public void applyGravity() {
        move(0, this.gravity);
    }

    @Override
    public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        if (this.enableGravity)
            applyGravity();
        if (!this.movements.isEmpty()) {
            float[] pts = this.movements.remove();
            move(pts[0], pts[1]);
        }
    }
}
