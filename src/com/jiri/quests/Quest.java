package com.jiri.quests;

import com.jiri.entities.Entity1D;

import java.util.*;

public abstract class Quest implements IQuest {
    Map<String, Integer> goalList;
    public List<Entity1D> watchedEntities;

    Quest() {
        this.goalList = new HashMap<>();
        this.watchedEntities = new ArrayList<>();
    }


    public void checkAllCompleted() {
        for (String goalKey : goalList.keySet()) {
            if (goalList.get(goalKey) > 0)
                return;
        }
        finishedQuest();
    }

    public void markProgress(String key) {
        String normalizedKey = key.toLowerCase();
        Integer count = goalList.get(normalizedKey);
        if (count != null && count > 0)
            goalList.replace(normalizedKey, count - 1);
        checkAllCompleted();
    }
}
