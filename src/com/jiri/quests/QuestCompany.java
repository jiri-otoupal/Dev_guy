package com.jiri.quests;


import com.jiri.entities.Entity1D;

public class QuestCompany extends Quest {
    public QuestCompany() {
        super();
        goalList.put("skeleton", 1);
        goalList.put("coffee", 1);
    }

    @Override
    public void finishedQuest() {
        Entity1D entity = watchedEntities.get(0);
        entity.visible = true;
        entity.render(entity.currentLevel.map, entity.absPosition);
    }
}
