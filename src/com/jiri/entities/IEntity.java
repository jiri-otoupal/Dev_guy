package com.jiri.entities;

import java.awt.*;

public interface IEntity {
    abstract public void useLight(); // Erases Shadows from movement

    abstract public boolean render(Entity1D[][] map, Point cursor);

    abstract public void tickEvent(long elapsedMs);
}
