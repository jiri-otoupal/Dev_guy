package com.jiri.entities;

import com.jiri.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 50);//nefunguje animace effektu impact
        this.animationState = new char[][][][]{{{{'>'}}, {{':'}}}};
        this.usedAnimationFrames = this.animationState[0];
    }
}
