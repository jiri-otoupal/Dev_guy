package com.jiri.entities;

import com.jiri.level.LevelStreamer;
import com.jiri.structures.PointExtended;

import java.awt.*;
import java.util.*;
import java.util.List;

public class AI {
    Set<Point> visitedPoints;
    public boolean closeEnoughToPlayer = false;
    public boolean needToJumpInPath = false;
    long searchFrequency;
    private final Enemy owner;
    private final Player player;
    int acceptableRadiusToPlayer;
    public List<PointExtended> pathToPlayer;

    AI(Enemy owner, Player player, int acceptableRadiusToPlayer, long searchingFrequency) {
        this.acceptableRadiusToPlayer = acceptableRadiusToPlayer;
        this.searchFrequency = searchingFrequency;
        this.visitedPoints = new HashSet<Point>();
        this.player = player;
        this.owner = owner;
        pathToPlayer = new ArrayList<PointExtended>();
        Thread thread = new Thread() {
            public void run() {
                periodicalSearch();
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    protected void periodicalSearch() {
        while (this.owner.health > 0) {
            //if (player.moving || player.falling || this.owner.moving || this.owner.falling) { // Run path finding only if someone is moving
            this.pathToPlayer = this.searchForPath(this.owner.currentLevel.levelStreamer);
            //}
            try {
                Thread.sleep(this.searchFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private boolean foundPlayer(Point location) {
        return location.equals(this.player.absPosition);
    }


    public java.util.List<PointExtended> searchForPath(LevelStreamer levelStreamer) {
        if (this.owner.absPosition == null)
            return Collections.emptyList();
        this.visitedPoints.clear();
        LinkedList<PointExtended> nextToVisit = new LinkedList<>();
        PointExtended start = new PointExtended(this.owner.absPosition, null);
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            PointExtended location = nextToVisit.remove();

            if (!levelStreamer.isValidLocation(location.point) || visitedPoints.contains(location.point)) {
                continue;
            }

            if (levelStreamer.getInstanceAt(location.point).persistent) {
                visitedPoints.add(location.point);
                continue;
            }

            if (foundPlayer(location.point)) {
                this.closeEnoughToPlayer = this.pathToPlayer.size() <= this.acceptableRadiusToPlayer;
                if (this.closeEnoughToPlayer && this.pathToPlayer.size() > 1 && !this.needToJumpInPath)
                    this.owner.attackIfClose();
                else if (this.pathToPlayer.size() > 1 && !this.closeEnoughToPlayer)
                    this.owner.moveOnPath();
                return backtrackPath(location);
            }

            for (Entity1D.Directions direction : Entity1D.Directions.values()) {
                {
                    PointExtended coordinate = new PointExtended(location.x() + direction.vector.x, location.y() + direction.vector.y, location);
                    nextToVisit.add(coordinate);
                    visitedPoints.add(location.point);
                }
            }

        }
        return Collections.emptyList();
    }

    private java.util.List<PointExtended> backtrackPath(PointExtended location) {
        List<PointExtended> path = new ArrayList<>();
        PointExtended currentLocation = location;
        this.needToJumpInPath = false;
        while (currentLocation != null) {
            if (currentLocation.point.y != this.owner.absPosition.y) {
                this.needToJumpInPath = true;
                currentLocation.requireJump = true;
            }
            path.add(currentLocation);
            currentLocation = currentLocation.parent;
        }


        return path;
    }
}
