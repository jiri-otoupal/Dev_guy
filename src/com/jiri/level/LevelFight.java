package com.jiri.level;

import com.jiri.entities.*;
import com.jiri.entities.items.Coffee;
import com.jiri.entities.items.Portal;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.persistent.Wall;
import com.jiri.entities.portals.PortalJungle;

import java.util.HashMap;

public class LevelFight extends Level {
    public LevelFight(int width, int height, LevelStreamer levelStreamer) throws InvalidTemplateMap {
        super(width, height, levelStreamer);
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
                "                                                                      l         ",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                             w                                i",
                "i                                             w                                i",
                "i                            c                w                                i",
                "i                                             w                                i",
                "                                                                                ",
        };
    }

    @Override
    public void initializeLinker() {
        Level level = this;
        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('p', new Player(level, 100, 1F, 50));
            put('e', new Skeleton(level, 50, 1F, 200, 6, 0.2F));
            put('c', new Coffee(level));
            put('l', new PortalJungle(level, false, "Portal"));
            put('i', new InvisibleWall(level));
        }};
    }
}
