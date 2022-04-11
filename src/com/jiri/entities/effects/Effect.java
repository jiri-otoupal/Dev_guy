package com.jiri.entities.effects;

import com.jiri.entities.Animated;
import com.jiri.level.Level;

public class Effect extends Animated {

    public Effect(Level currentLevel, float lifeSpanMs, long animationRateMs) {
        super(currentLevel, false, lifeSpanMs);
        this.persistent = false;
        this.lifeSpan = lifeSpanMs;
        this.loops = true;
        this.frameCounter = 0;
        this.frameDurationMs = animationRateMs;
    }


    @Override
    public void decayEffectFromItems(long ticksMs) {

    }
}
