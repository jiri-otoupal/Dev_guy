package com.jiri.entities.items;

import com.jiri.level.Level;

public class Coffee extends Item {
    public Coffee(Level currentLevel) {
        super(currentLevel, false);
        this.animationState = new char[][][][]{{{{'c', '[', '_', ']'}}, {{'c', '[', '▁', ']'}}, {{'c', '[', '▂', ']'}}, {{'c', '[', '▃', ']'}}, {{'c', '[', '▄', ']'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 10;
    }

    @Override
    public boolean use() {
        return false;
    }
}
