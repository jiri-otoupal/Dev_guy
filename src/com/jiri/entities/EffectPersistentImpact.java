package com.jiri.entities;

import com.jiri.level.Level;

public class EffectPersistentImpact extends Effect {
    public EffectPersistentImpact(Level currentLevel, float lifeSpanMs) {
        super(currentLevel, lifeSpanMs, 50);
        this.animationFrames = new char[][][]{{{'>'}}, {{':'}}};
        this.representMap = animationFrames[this.frameCounter];
    }



}
