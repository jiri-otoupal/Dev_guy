package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Animated extends Movable {
    protected long elapsedNow = 0;
    protected long elapsedMsToFrame;
    public char[][][] usedAnimationFrames;
    public char[][][][] animationState; // Idle, Move, Shooting, etc...
    public int frameCounter;
    public boolean loops;
    public int currentAnimationState = 0;
    protected boolean canGetOlder; // timeSpan is decremented if true

    public Animated(Level currentLevel, boolean enableGravity, boolean canGetOlder) {
        super(currentLevel, enableGravity);
        if (usedAnimationFrames == null) {
            this.usedAnimationFrames = new char[][][]{this.representMap};
            this.animationState = new char[][][][]{usedAnimationFrames};
        } else {
            this.representMap = animationState[currentAnimationState][frameCounter];
        }
        this.animationListeners.add(this);
        this.canGetOlder = canGetOlder;
    }

    public void updateParticles() {
        this.currentLevel.levelStreamer.spawnAt(this.absPosition, this);
    }

    public void nextFrame() {
        if (this.frameCounter < this.usedAnimationFrames.length) {
            this.usedAnimationFrames = animationState[currentAnimationState];
            this.representMap = this.usedAnimationFrames[frameCounter];
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
        this.usedAnimationFrames = animationState[currentAnimationState];
        nextFrame();
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
        if (usedAnimationFrames.length > 1 && elapsedNow >= elapsedMsToFrame)
            this.nextFrame();
    }


}
