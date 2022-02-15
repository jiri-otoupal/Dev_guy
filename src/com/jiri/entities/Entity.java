package com.jiri.entities;

import java.awt.*;

public class Entity implements IEntity {
    protected char[][] representMap; // Characters representing entity on rendered map
    public Point position;
    public boolean persistent; // If True object is not passable by Entities

    public char[][] getRepresentMap() {
        return representMap;
    }

    public char getChar() {
        return ' ';
    }

    public boolean isColliding() {
        return false;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public Point getPosition() {
        return position;
    }


    @Override
    public boolean render(Entity[][] map, Point cursor) {
        for (int map_x = cursor.x, ent_x = 0; ent_x < representMap[0].length; map_x++, ent_x++) {
            for (int map_y = cursor.y, ent_y = 0; ent_y < representMap.length; map_y++, ent_y++) {
                map[map_y][map_x] = new EntityShadow(representMap[ent_y][ent_x], this);
            }
        }
        return true;
    }
}
