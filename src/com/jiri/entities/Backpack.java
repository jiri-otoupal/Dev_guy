package com.jiri.entities;

import java.util.HashMap;
import java.util.Map;

public class Backpack {
    int maxSize;
    Map<Item, Integer> items;


    public Backpack(int backpackSize) {
        this.items = new HashMap<Item, Integer>();
        this.maxSize = backpackSize;
    }

    public boolean insertItem(Item item) {
        if (this.items.size() >= maxSize)
            return false;
        if (this.items.containsKey(item)) {
            Integer storedCount = this.items.get(item);
            this.items.put(item, storedCount + 1);
        } else {
            this.items.put(item, 1);
        }
        return true;
    }

    public boolean removeItem(Item item) {
        if (this.items.containsKey(item)) {
            Integer storedCount = this.items.get(item);
            if (storedCount == 1)
                this.items.remove(item);
            this.items.put(item, storedCount - 1);
            return true;
        }
        return false;
    }

    public int getAmount(Item item) {
        if (!this.items.containsKey(item))
            return 0;
        return this.items.get(item);
    }
}
