package com.jiri.entities;

import com.jiri.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 25);//nefunguje animace effektu impact
        this.animationState = new char[][][][]{{{{'>'}}, {{':'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
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
