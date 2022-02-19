package com.jiri.level;

import com.jiri.entities.*;

import java.util.HashMap;

public class LevelFight extends Level {
    public LevelFight(int width, int height, LevelStreamer levelStreamer) throws InvalidTemplateMap {
        super(width, height, levelStreamer);
        Level level = this;
        this.linker = new HashMap<Character, Entity1D>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('p', new Player(level, 100, 1F, 100));
            put('e', new Skeleton(level, 50, 1F, 100, 6, 0.2F));
            put('i', new InvisibleWall(level));
        }};
        this.mapToTranslate = new String[]{
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                p                                   e                           ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "i                                                                               ",
                "i                                             w                                 ",
                "i                                             w                                 ",
                "i                                             w                                 ",
                "i                                             w                                 ",
                "                                                                                ",
        };
        this.compileMap();
    }
}
