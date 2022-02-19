package com.jiri.entities.items;

import com.jiri.entities.Animated;
import com.jiri.entities.BannerText;
import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

public abstract class Item extends Animated implements IItem {
    boolean instant; //Cannot be grabbed, its used immediately
    String name;

    public Item(Level currentLevel, boolean instant, String name) {
        super(currentLevel, false);
        this.loops = true;
        this.instant = instant;
        this.name = name;
    }


    @Override
    public boolean grab(Entity1D instigator) {

        new BannerText(currentLevel, "Got " + name + "+1", false, 45, 200).spawnAtPlayer();
        if (instigator.canGrabItem()) {
            if (this.instant) {
                this.use();
                this.erase();
                return true;
            }
            if (!instigator.insertItemToBackpack(this))
                return false;
            this.erase();
            return true;
        }
        return false;
    }

}
