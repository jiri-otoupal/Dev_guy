package com.jiri.entities;

import com.jiri.level.Level;

public class Effect extends Movable {
    protected long elapsedNow = 0;
    protected long elapsedMsToFrame;
    public char[][][] animationFrames;
    public int frameCounter;
    public boolean loops;

    public Effect(Level currentLevel, float lifeSpanMs, long animationRateMs) {
        super(currentLevel, false);
        this.persistent = false;
        this.lifeSpan = lifeSpanMs;
        this.loops = true;
        this.frameCounter = 0;
        this.elapsedMsToFrame = animationRateMs;
    }

    public void updateParticles() {
        for (int line = 0, index = 0; line < this.representMap.length; line++, index++)
            for (int column = 0; column < this.representMap[0].length; column++, index++)
                this.currentLevel.levelStreamer.getInstanceAt(bodyPositions.get(index)).representingChar = this.representMap[line][column];
    }

    public void nextFrame() {
        if (this.frameCounter < this.animationFrames.length) {
            this.representMap = animationFrames[this.frameCounter];
            this.frameCounter++;
        } else if (this.loops) {
            this.frameCounter = 0;
        }
        elapsedNow = 0;
        updateParticles();
    }

    @Override
    public void tickEvent(long elapsedMs) {
        this.elapsedNow += elapsedMs + 1;
        this.lifeSpan -= elapsedMs + 1;
        if (animationFrames.length > 1 && elapsedNow >= elapsedMsToFrame)
            this.nextFrame();
    }
}
