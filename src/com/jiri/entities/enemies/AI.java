package com.jiri.entities.enemies;

import com.jiri.entities.Entity1D;
import com.jiri.entities.Player;
import com.jiri.level.Streamer;
import com.jiri.structure.PointExtended;

import java.awt.*;
import java.util.List;
import java.util.*;

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

    /**
     * Does Search in Map for player
     */
    protected void periodicalSearch() {
        while (this.owner.health > 0) {
            if ((!this.pathToPlayer.isEmpty() && !player.absPosition.equals(this.pathToPlayer.get(0)) || player.falling || this.owner.movements.isEmpty() || this.owner.falling) && owner.currentLevel.streamer != null) { // Run path finding only if someone is moving
                this.pathToPlayer = this.searchForPath(this.owner.currentLevel.streamer);
            }
            try {
                Thread.sleep(this.searchFrequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Check if player was found
     *
     * @param location of player
     * @return True if player found at location
     */
    private boolean foundPlayer(Point location) {
        return location.equals(this.player.absPosition);
    }


    /**
     * Search for Path to Player
     *
     * @param streamer to search in
     * @return path to player
     */
    public java.util.List<PointExtended> searchForPath(Streamer streamer) {
        if (this.owner.absPosition == null)
            return Collections.emptyList();
        this.visitedPoints.clear();
        LinkedList<PointExtended> nextToVisit = new LinkedList<>();
        PointExtended start = new PointExtended(this.owner.absPosition, null);
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) {
            PointExtended location = nextToVisit.remove();

            if (!streamer.isValidLocation(location.point) || visitedPoints.contains(location.point)) {
                continue;
            }

            if (streamer.getInstanceAt(location.point).persistent) {
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

    /**
     * Make route from location for forwarding
     *
     * @param location location to backtrack from
     * @return backtracked path
     */
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
