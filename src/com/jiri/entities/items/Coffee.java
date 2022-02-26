package com.jiri.entities.items;

import com.jiri.entities.Entity1D;
import com.jiri.entities.Player;
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
        Player player = (Player) instigator;
        player.activeItems.put(itemName, effectTicksMsLast);
        player.sayStatic("Activated " + itemName);
        player.fireRate = 75;
        return true;
    }

}
