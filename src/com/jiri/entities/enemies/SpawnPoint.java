package com.jiri.entities.enemies;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class SpawnPoint extends Entity1D {
    private final int maxSpawnedAtOnce;
    long frequencyMs;
    long count = 0;
    List<BlackStone> blackStoneList;

    public SpawnPoint(Level currentLevel, long frequencyMs, int maxSpawnedAtOnce) {
        super(currentLevel);
        representMap = new char[][]{{' '}};
        this.frequencyMs = frequencyMs;
        this.count = frequencyMs;
        blackStoneList = new ArrayList<>();
        this.currentLevel.streamer.addListener(this);
        this.maxSpawnedAtOnce = maxSpawnedAtOnce;
    }

    public void checkAndDeleteDead() {
        blackStoneList.removeIf(blackStone -> (blackStone.dead));
    }

    @Override
    public void tickEvent(long elapsedMs) {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;
        this.count -= correctedElapsed;

        if (count <= 0) {
            checkAndDeleteDead();
            if (blackStoneList.size() < maxSpawnedAtOnce) {
                BlackStone enemy = new BlackStone(this.currentLevel);
                blackStoneList.add(enemy);
                currentLevel.streamer.assignAt(new Point(this.absPosition.x, this.absPosition.y - 1), enemy);
            }
            count = this.frequencyMs;
        }
    }
}
