package com.jiri.entities;

import java.awt.*;

public interface IEntity {
    abstract public boolean render(Entity[][] map, Point cursor);
}
