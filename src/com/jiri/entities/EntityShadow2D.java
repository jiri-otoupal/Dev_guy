package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.Point;

public class EntityShadow2D extends Entity1D {
    char representingChar;

    public EntityShadow2D(Level currentLevel, char c, Entity1D entity_ptr) {
        super(currentLevel);
        this.representingChar = c;
        this.shadow_parent = entity_ptr;
        this.persistent = entity_ptr.persistent;
    }

    @Override
    public char getChar() {
        return representingChar;
    }

    @Override
    public boolean render(Entity1D[][] map, Point cursor) {
        return false;
    }


}
