package com.jiri.entities;

public interface IAnimation {

    abstract public void updateAnimation(long elapsedMs);
    abstract public int resolveAnimationState();
    abstract public void setAnimationState(int state);
}
