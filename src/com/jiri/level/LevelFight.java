package com.jiri.level;

import com.jiri.entities.*;
import com.jiri.entities.items.Coffee;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.persistent.Wall;

import java.util.HashMap;

public class LevelFight extends Level {
    public LevelFight(int width, int height, LevelStreamer levelStreamer) throws InvalidTemplateMap {
        super(width, height, levelStreamer);
        Level level = this;
        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('p', new Player(level, 100, 1F, 50));
            put('e', new Skeleton(level, 200, 1F, 500, 6, 0.2F));
            put('c', new Coffee(level));
            put('i', new InvisibleWall(level));
        }};
        this.mapToTranslate = new String[]{
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                p                                                               ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                               e                ",
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
                "i                            c                w                                 ",
                "i                                             w                                 ",
                "                                                                                ",
        };
        this.compileMap();
    }
}
