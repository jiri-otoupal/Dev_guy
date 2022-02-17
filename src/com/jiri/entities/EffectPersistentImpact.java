package com.jiri.entities;

import com.jiri.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 25);
        this.usedAnimationFrames = new char[][][]{{{'>'}}, {{':'}}};
        this.representMap = usedAnimationFrames[this.frameCounter];
    }
}
