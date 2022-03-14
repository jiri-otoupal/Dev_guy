package com.jiri.level;

import com.jiri.entities.persistent.EmptySpace;
import com.jiri.quests.QuestCompany;

import java.util.HashMap;

public class CheckerTable extends Level {
    public CheckerTable(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Checkers", width, height, streamer);
        this.mapToTranslate = new String[]{
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "                                                                                ",
        };
        this.quest = new QuestCompany();
    }


    @Override
    public void initializeLinker() {
        Level level = this;

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
        }};

    }
}
