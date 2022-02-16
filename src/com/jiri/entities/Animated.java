package com.jiri.entities;

import com.jiri.level.Level;


public class Animated extends Movable {
    protected long elapsedNow = 0;
    protected long elapsedMsToFrame;
    public char[][][] animationFrames;
    public int frameCounter;
    public boolean loops;
    protected boolean canGetOlder; // timeSpan is decremented if true

    public Animated(Level currentLevel, boolean enableGravity, boolean canGetOlder) {
        super(currentLevel, enableGravity);
        if (animationFrames == null)
            animationFrames = new char[][][]{this.representMap};
        animationListeners.add(this);
        this.canGetOlder = canGetOlder;
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
    public void removeConnections() {
        this.currentLevel.levelStreamer.removeListener(this);
        this.animationListeners.remove(this);
    }

    @Override
    public void updateAnimation(long elapsedMs) {
        long correctedElapsed = elapsedMs + 1;//+1 in case it takes 0ms to render frame
        this.elapsedNow += correctedElapsed;
        if (canGetOlder)
            this.lifeSpan -= correctedElapsed;
        // Do next frame only if there is more than 1 frame
        if (animationFrames.length > 1 && elapsedNow >= elapsedMsToFrame)
            this.nextFrame();
    }


}
