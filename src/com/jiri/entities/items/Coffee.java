package com.jiri.entities.items;

import com.jiri.entities.Entity1D;
import com.jiri.entities.PlayerStone;
import com.jiri.level.Level;

public class Coffee extends ItemWithEffect {
    public Coffee(Level currentLevel) {
        super(currentLevel, false, "Coffee", 2500);
        this.animationState = new char[][][][]{{{{'c', '[', '_', ']'}}, {{'c', '[', '▁', ']'}}, {{'c', '[', '▂', ']'}}, {{'c', '[', '▃', ']'}}, {{'c', '[', '▄', ']'}}}};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 25;
    }

    @Override
    public boolean use(Entity1D instigator) {
        // Only player can use coffee this cast is safe
        PlayerStone playerStone = (PlayerStone) instigator;
        playerStone.sayStatic("Used " + itemName);
        playerStone.fireRate = 75;
        return true;
    }

}
