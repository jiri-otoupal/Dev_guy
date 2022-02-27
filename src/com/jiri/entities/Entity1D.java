package com.jiri.entities;


import com.jiri.entities.items.Item;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.textrender.Text;
import com.jiri.level.Level;
import com.jiri.structure.ForceVector;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Entity1D implements IEntity {
    protected char[][] representMap; // Characters representing entity on rendered map
    protected Set<Point> bodyPositions; //Positions in map for collisions
    public Point absPosition; // Absolute position on map before render
    public boolean persistent; // If True object is not passable by Entities
    public Level currentLevel;
    public Entity1D shadow_parent = this;
    public Map<Point, Entity1D> collisionDirections;
    public boolean falling = true;
    public boolean facingLeft;
    public char muzzleChar = 'c';
    public Point textRenderPoint;
    public List<Point> muzzlePoints = new ArrayList<>(2);
    public float lifeSpan = 0;
    public char representingChar;
    protected char textRenderChar = 'T';
    public Text currentRenderedText;
    protected boolean markedForErase = false;
    public boolean visible = true;


    public char[][] getRepresentMap() {
        return representMap;
    }

    public enum Directions {
        Top(0, -1),
        Bottom(0, 1),
        Left(-1, 0),
        Right(1, 0);


        public final Point vector;

        Directions(int x, int y) {
            vector = new Point(x, y);
        }
    }

    public Entity1D(Level currentLevel) {
        bodyPositions = new HashSet<>();
        this.currentLevel = currentLevel;
        this.collisionDirections = new HashMap<>();
        representMap = new char[][]{{' '}};
    }

    public char getChar() {
        return ' ';
    }

    protected void isBodyPartColliding(Point partPosition) {
        Point[] dirScan = new Point[]{Directions.Right.vector, Directions.Left.vector, Directions.Top.vector, Directions.Bottom.vector}; // (Y,X) directions to scan Bottom,Top,Left,Right
        for (Point delta : dirScan) {
            Point scannedPt = new Point(partPosition.x + delta.x, partPosition.y + delta.y);
            if (scannedPt.x >= this.currentLevel.width || scannedPt.y >= this.currentLevel.height)
                continue;
            if (this.currentLevel.streamer == null)
                return;
            Entity1D scanned = this.currentLevel.streamer.getInstanceAt(scannedPt);
            try {
                if (scanned.shadow_parent != this && scanned.getChar() != ' ' && (!scanned.shadow_parent.grab(this)) && scanned.shadow_parent.canCollide() && scanned.shadow_parent.visible)
                    collisionDirections.put(delta, scanned);

            } catch (Level.InvalidTemplateMap e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Map<Point, Entity1D> isColliding() {
        collisionDirections.clear();
        if (currentLevel == null)
            return collisionDirections;
        for (Point position : bodyPositions)
            isBodyPartColliding(position);
        this.falling = !collisionDirections.containsKey(Directions.Bottom.vector);
        return collisionDirections;
    }


    @Override
    public boolean render(Entity1D[][] map, Point cursor) {
        if (markedForErase)
            return false;
        muzzlePoints.clear();
        bodyPositions.clear();
        for (int map_x = cursor.x, ent_x = 0; ent_x < representMap[0].length; map_x++, ent_x++) {
            for (int map_y = cursor.y, ent_y = 0; ent_y < representMap.length; map_y++, ent_y++) {
                iterationRenderFunction(map, cursor, map_x, map_y, ent_x, ent_y);
            }
        }
        return true;
    }

    @Override
    public void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y) {
        char currentRenderedChar = representMap[ent_y][ent_x];
        if (currentRenderedChar == ' ' || (map[map_y][map_x].persistent && !this.persistent))
            return;
        else if (currentRenderedChar == muzzleChar)
            muzzlePoints.add(new Point(map_x, map_y));
        else if (currentRenderedChar == textRenderChar) { //assign text render point for character Default character 'T'
            this.textRenderPoint = new Point(map_x, map_y);
            return;
        }
        map[map_y][map_x] = new EntityShadow2D(this.currentLevel, currentRenderedChar, this);
        bodyPositions.add(new Point(map_x, map_y));
    }

    @Override
    public void tickEvent(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
    }

    @Override
    public boolean applyDamage(float damage) {
        return false;
    }

    @Override
    public boolean applyPhysicsImpulse(float mass, ForceVector vector) {
        return false;
    }

    @Override
    public float getLifeSpan() {
        return this.lifeSpan;
    }

    @Override
    public void invokeImpactEffect(Point impactLocation) {

    }

    public void removeConnections() {
        this.currentLevel.streamer.removeListener(this);
    }

    @Override
    public boolean canGrabItem() {
        return false;
    }

    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        return false;
    }

    @Override
    public boolean insertItemToBackpack(Item item) {
        return false;
    }

    @Override
    public boolean canCollide() {
        return true;
    }

    @Override
    public void erase() {
        markedForErase = true;
        removeConnections();
        for (Point partPoint : bodyPositions) {
            this.currentLevel.map[partPoint.y][partPoint.x] = new EmptySpace(this.currentLevel);
        }
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
