package com.jiri.level;

import com.jiri.entities.*;
import com.jiri.entities.enemies.Skeleton;
import com.jiri.entities.items.Coffee;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.persistent.Wall;
import com.jiri.entities.portals.PortalJungle;
import com.jiri.entities.props.background.Computer;
import com.jiri.entities.props.background.Window;
import com.jiri.volumes.SpawnVolume;

import java.util.HashMap;

public class CompanyFight extends Level {
    public CompanyFight(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Escape", width, height, streamer);
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
                "i     o                                              u                         i",
                "i                                                                              i",
                "iv                          c                                                  i",
                "i                        k                                                     i",
                "i                                             w                                i",
                "i                                             w                                i",
                "i                                             w                                i",
                "i                                             w                                i",
                "                                                                                ",
        };
    }


    @Override
    public void initializeLinker() {
        Level level = this;
        if (player == null)
            player = new Player(level, 100, 1F, 50);
        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('o', new Window(level));
            put('u', new Window(level));
            put('k', new Computer(level));
            put('p', player);
            put('e', new Skeleton(level, 50, 1F, 200, 6, 0.2F));
            put('c', new Coffee(level));
            put('l', new PortalJungle(level, false, "Portal"));
            put('i', new InvisibleWall(level));
            put('v', new SpawnVolume(level, 30, 5, "Spawn"));
        }};

    }
}
