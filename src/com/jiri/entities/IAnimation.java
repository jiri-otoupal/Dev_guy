package com.jiri.entities;

public interface IAnimation {

    void updateAnimation(long elapsedMs);
    int resolveAnimationState();
    void setAnimationState(int state);
}
