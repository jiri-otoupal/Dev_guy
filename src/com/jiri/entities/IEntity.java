package com.jiri.entities;

import com.jiri.entities.items.Item;
import com.jiri.level.Level;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public interface IEntity {
    void useLight(); // Erase Shadows from movement

    boolean render(Entity1D[][] map, Point cursor);

    void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y);

    void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    boolean applyDamage(float damage);

    boolean applyPhysicsImpulse(float mass);

    float getLifeSpan();

    void erase();

    void invokeImpactEffect(Point impactLocation) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Set<Point> isColliding();

    void removeConnections();

    boolean canGrabItem();

    boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap;

    boolean insertItemToBackpack(Item item);
}
