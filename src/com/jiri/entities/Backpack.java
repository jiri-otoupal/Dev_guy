package com.jiri.entities;

import com.jiri.entities.items.Item;
import com.jiri.structure.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class Backpack {
    public int maxSize;
    public Map<String, Pair<Item, Integer>> items;


    public Backpack(int backpackSize) {
        this.items = new HashMap<>();
        this.maxSize = backpackSize;
    }

    public boolean insertItem(Item item) {
        if (this.items.size() >= maxSize)
            return false;
        if (this.items.containsKey(item.itemName)) {
            Integer storedCount = this.items.get(item.itemName).second;
            this.items.put(item.itemName, new Pair<>(item, storedCount + 1));
        } else {
            this.items.put(item.itemName, new Pair<>(item, 1));
        }
        return true;
    }

    public @Nullable Pair<Item, Integer> removeItem(String itemName) {
        if (this.items.containsKey(itemName)) {
            return this.items.remove(itemName);
        }
        return null;
    }

    public int getAmount(String itemName) {
        if (!this.items.containsKey(itemName))
            return 0;
        return this.items.get(itemName).second;
    }

    public int getAmount(Item item) {
        if (!this.items.containsKey(item.itemName))
            return 0;
        return this.items.get(item.itemName).second;
    }
}
