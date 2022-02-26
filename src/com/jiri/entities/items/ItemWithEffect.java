package com.jiri.entities.items;

import com.jiri.level.Level;

public abstract class ItemWithEffect extends Item {
    protected long effectTicksMsLast;

    public ItemWithEffect(Level currentLevel, boolean instant, String quest_name, long effectTicksMsLast) {
        super(currentLevel, instant, quest_name);
        this.effectTicksMsLast = effectTicksMsLast;
    }
}
