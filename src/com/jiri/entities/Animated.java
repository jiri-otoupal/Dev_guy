package com.jiri.entities;

import com.jiri.level.Level;


public class Animated extends Movable {
    protected long elapsedNow = 0;
    protected long frameDurationMs;
    public char[][][] selectedAnimationFrames;
    public char[][][][] animationState; // Idle, Move, Shooting, etc...
    public int frameCounter;
    public boolean loops;
    public int currentAnimationState = 0;
    protected boolean canGetOlder; // timeSpan is decremented if true

    public Animated(Level currentLevel, boolean enableGravity) {
        super(currentLevel, enableGravity);
        if (selectedAnimationFrames == null) {
            this.selectedAnimationFrames = new char[][][]{this.representMap};
            this.animationState = new char[][][][]{selectedAnimationFrames};
        } else {
            this.representMap = animationState[currentAnimationState][frameCounter];
        }
        this.animationListeners.add(this);
        this.canGetOlder = false;
    }

    public Animated(Level currentLevel, boolean enableGravity, float lifeSpan) {
        super(currentLevel, enableGravity);
        if (selectedAnimationFrames == null) {
            this.selectedAnimationFrames = new char[][][]{this.representMap};
            this.animationState = new char[][][][]{selectedAnimationFrames};
        } else {
            this.representMap = animationState[currentAnimationState][frameCounter];
        }
        this.animationListeners.add(this);
        this.lifeSpan = lifeSpan;
        this.canGetOlder = true;
    }

    public void updateParticles() {
        this.currentLevel.levelStreamer.assignAt(this.absPosition, this);
    }

    public void nextFrame() {
        if (this.frameCounter < this.selectedAnimationFrames.length) {
            this.selectedAnimationFrames = animationState[currentAnimationState];
            this.representMap = this.selectedAnimationFrames[frameCounter];
            this.frameCounter++;
        } else if (this.loops) {
            this.frameCounter = 0;
        }
        elapsedNow = 0;
        updateParticles();
    }

    @Override
    public void setAnimationState(int state) {
        if (this.currentAnimationState == state)
            return;
        this.currentAnimationState = state;
        this.selectedAnimationFrames = animationState[currentAnimationState];
        nextFrame();
    }

    @Override
    public void removeConnections() {
        this.currentLevel.levelStreamer.removeListener(this);
        this.animationListeners.remove(this);
    }

    @Override
    public void updateAnimation(long elapsedMs) {
        long correctedElapsed = elapsedMs == 0 ? 1 : elapsedMs;//1 in case it takes 0ms to render frame

        this.elapsedNow += correctedElapsed;
        if (canGetOlder)
            this.lifeSpan -= correctedElapsed;
        // Do next frame only if there is more than 1 frame
        if (selectedAnimationFrames.length > 1 && elapsedNow >= frameDurationMs)
            this.nextFrame();
    }


}
