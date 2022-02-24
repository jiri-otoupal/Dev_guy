package com.jiri.entities.enemies;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class SpawnPoint extends Entity1D {
    long frequencyMs;
    long count = 0;
    List<Skeleton> skeletonList;

    public SpawnPoint(Level currentLevel, long frequencyMs) {
        super(currentLevel);
        representMap = new char[][]{{' '}};
        this.frequencyMs = frequencyMs;
        this.count = frequencyMs;
        skeletonList = new ArrayList<>();
        this.currentLevel.streamer.addListener(this);
    }

    public void checkAndDeleteDead() {
        skeletonList.removeIf(skeleton -> (skeleton.dead));
    }

    @Override
    public void tickEvent(long elapsedMs) {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;
        this.count -= correctedElapsed;

        if (count <= 0) {
            checkAndDeleteDead();
            if (skeletonList.size() < 3) {
                Skeleton enemy = new Skeleton(this.currentLevel, 50, 1F, 200, 6, 0.2F);
                skeletonList.add(enemy);
                currentLevel.streamer.assignAt(new Point(this.absPosition.x, this.absPosition.y - 1), enemy);
            }
            count = this.frequencyMs;
        }
    }
}
