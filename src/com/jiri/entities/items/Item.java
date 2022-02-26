package com.jiri.entities.items;

import com.jiri.entities.Animated;
import com.jiri.entities.textrender.DialogText;
import com.jiri.entities.Entity1D;
import com.jiri.level.Level;


public abstract class Item extends Animated implements IItem {
    boolean instant; //Cannot be grabbed, its used immediately
    public String quest_name; //Used for Quest id also

    public Item(Level currentLevel, boolean instant, String quest_name) {
        super(currentLevel, false);
        this.loops = true;
        this.instant = instant;
        this.quest_name = quest_name;
    }

    @Override
    public boolean canCollide() {
        return false;
    }

    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        if (instigator.canGrabItem()) {
            if (this.instant) {
                this.use();
                this.erase();
                new DialogText(currentLevel, "Used " + quest_name, false, 45, 200).spawnAtPlayer();
                this.currentLevel.quest.markProgress(this.quest_name);
                return true;
            }
            if (!instigator.insertItemToBackpack(this))
                return false;
            this.erase();
            new DialogText(currentLevel, "Got " + quest_name + "+1", false, 45, 200).spawnAtPlayer();
            if (currentLevel.quest != null)
                this.currentLevel.quest.markProgress(this.quest_name);
            return true;
        }
        return false;
    }

}
