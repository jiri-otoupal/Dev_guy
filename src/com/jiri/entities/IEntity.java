package com.jiri.entities;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public interface IEntity {
    abstract public void useLight(); // Erase Shadows from movement

    abstract public boolean render(Entity1D[][] map, Point cursor);

    abstract public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    abstract public boolean applyDamage(float damage);

    abstract public boolean applyPhysicsImpulse(float mass);

    abstract public float getLifeSpan();

    abstract public void erase();

    abstract public void invokeImpactEffect(Point impactLocation) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

}
