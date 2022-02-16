package com.jiri.entities;


import com.jiri.level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Entity1D implements IEntity {
    protected char[][] representMap; // Characters representing entity on rendered map
    protected List<Point> bodyPositions; //Positions in map for collisions
    public Point absPosition; // Absolute position on map before render
    public boolean persistent; // If True object is not passable by Entities
    public Level currentLevel;
    public Entity1D shadow_parent = null;
    public Set<Point> collisions;
    public boolean standingOnPersistent = false;

    public char[][] getRepresentMap() {
        return representMap;
    }

    public enum Direction {
        Top(0, -1),
        Bottom(0, 1),
        Left(-1, 0),
        Right(1, 0);


        private final Point vector;

        Direction(int x, int y) {
            vector = new Point(x, y);
        }
    }

    public Entity1D(Level currentLevel) {
        bodyPositions = new ArrayList<Point>();
        this.currentLevel = currentLevel;
        this.collisions = new HashSet<Point>();
    }

    public char getChar() {
        return ' ';
    }

    protected void isBodyPartColliding(Point partPosition) {
        Point[] dirScan = new Point[]{Direction.Right.vector, Direction.Left.vector, Direction.Top.vector, Direction.Bottom.vector}; // (Y,X) directions to scan Bottom,Top,Left,Right
        for (Point delta : dirScan) {
            Point scannedPt = new Point(partPosition.x + delta.x, partPosition.y + delta.y);
            if (scannedPt.x >= this.currentLevel.width || scannedPt.y >= this.currentLevel.height)
                continue;
            Entity1D scanned = this.currentLevel.map[scannedPt.y][scannedPt.x];
            if (scanned.shadow_parent != this && scanned.persistent)
                collisions.add(delta);
        }

    }

    public Set<Point> isColliding() {
        collisions.clear();
        if (currentLevel == null)
            return collisions;
        for (Point position : bodyPositions)
            isBodyPartColliding(position);
        if (collisions.contains(Direction.Bottom.vector))
            this.standingOnPersistent = true;
        else
            this.standingOnPersistent = false;
        return collisions;
    }

    public boolean isPersistent() {
        return persistent;
    }


    @Override
    public boolean render(Entity1D[][] map, Point cursor) {
        bodyPositions.clear();
        for (int map_x = cursor.x, ent_x = 0; ent_x < representMap[0].length; map_x++, ent_x++) {
            for (int map_y = cursor.y, ent_y = 0; ent_y < representMap.length; map_y++, ent_y++) {
                if (representMap[ent_y][ent_x] == ' ')
                    continue;
                map[map_y][map_x] = new EntityShadow2D(this.currentLevel, representMap[ent_y][ent_x], this);
                bodyPositions.add(new Point(map_x, map_y));
            }
        }
        return true;
    }

    @Override
    public void tickEvent(long elapsedMs) {
    }

    @Override
    public void useLight() {
        if (persistent)
            return;
        for (Point partPoint : bodyPositions) {
            this.currentLevel.map[partPoint.y][partPoint.x] = new EmptySpace(this.currentLevel);
        }
    }
}
