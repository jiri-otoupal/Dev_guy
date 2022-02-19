package com.jiri.entities.effects;

import com.jiri.entities.Animated;
import com.jiri.entities.Player;
import com.jiri.level.Level;

public class Effect extends Animated {

    public Effect(Level currentLevel, float lifeSpanMs, long animationRateMs) {
        super(currentLevel, false, true);
        this.persistent = false;
        this.lifeSpan = lifeSpanMs;
        this.loops = true;
        this.frameCounter = 0;
        this.frameDurationMs = animationRateMs;
    }


}
