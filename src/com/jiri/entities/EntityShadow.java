package com.jiri.entities;

import java.awt.*;

public class EntityShadow extends Entity {
    char representingChar;
    public Entity shadow_parent;

    public EntityShadow(char c, Entity entity_ptr) {
        super();
        this.representingChar = c;
        this.shadow_parent = entity_ptr;
    }

    @Override
    public char getChar() {
        return representingChar;
    }

    @Override
    public boolean render(Entity[][] map, Point cursor) {
        return false;
    }
}
