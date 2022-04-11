package com.jiri.entities.enemies;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SpawnPoint extends Entity1D {
    private final int maxSpawnedAtOnce;
    long frequencyMs;
    long count = 0;
    List<Skeleton> skeletonList;

    public SpawnPoint(Level currentLevel, long frequencyMs, int maxSpawnedAtOnce) {
        super(currentLevel);
        representMap = new char[][]{{' '}};
        this.frequencyMs = frequencyMs;
        this.count = frequencyMs;
        skeletonList = new ArrayList<>();
        this.currentLevel.streamer.addListener(this);
        this.maxSpawnedAtOnce = maxSpawnedAtOnce;
    }

    /**
     * Checks and delete dead skeleton, later garbage collected
     */
    public void checkAndDeleteDead() {
        skeletonList.removeIf(skeleton -> (skeleton.dead));
    }

    /**
     * Event called every tick if object subscribed to ticks
     *
     * @param elapsedMs between events
     */
    @Override
    public void tickEvent(long elapsedMs) {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;
        this.count -= correctedElapsed;

        if (count <= 0) {
            checkAndDeleteDead();
            if (skeletonList.size() < maxSpawnedAtOnce) {
                Skeleton enemy = new Skeleton(this.currentLevel, 50, 1F, 200, 6, 0.2F);
                skeletonList.add(enemy);
                currentLevel.streamer.assignAt(new Point(this.absPosition.x, this.absPosition.y - 1), enemy);
            }
            count = this.frequencyMs;
        }
    }
}
