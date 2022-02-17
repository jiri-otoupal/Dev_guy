package com.jiri.entities;

import com.jiri.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 50);//nefunguje animace effektu impact
        this.animationState = new char[][][][]{{{{'>'}}, {{':'}}}};
        this.usedAnimationFrames = this.animationState[this.currentAnimationState];
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
