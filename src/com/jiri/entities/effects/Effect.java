package com.jiri.entities.effects;

import com.jiri.entities.Animated;
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
