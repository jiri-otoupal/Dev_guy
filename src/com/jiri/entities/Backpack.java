package com.jiri.entities;

import com.jiri.entities.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Backpack {
    public int maxSize;
    public Map<Item, Integer> items;


    public Backpack(int backpackSize) {
        this.items = new HashMap<>();
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

    public Item removeItem(String itemName) {
        if (!this.items.isEmpty()) {
            for (Item item : items.keySet()) {
                if (item.itemName.equals(itemName)) {
                    Integer storedCount = this.items.get(item);
                    if (storedCount <= 1)
                        this.items.remove(item);
                    else
                        this.items.put(item, storedCount - 1);
                    return item;
                }
            }
        }
        return null;
    }

    public int getAmount(Item item) {
        if (!this.items.containsKey(item))
            return 0;
        return this.items.get(item);
    }
}
