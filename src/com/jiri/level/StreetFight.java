package com.jiri.level;

import com.jiri.entities.Player;
import com.jiri.entities.Skeleton;
import com.jiri.entities.items.Coffee;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.persistent.Wall;
import com.jiri.entities.portals.PortalJungle;
import com.jiri.volumes.SpawnVolume;

import java.util.HashMap;

public class StreetFight extends Level {
    public StreetFight(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Street Fight", width, height, streamer);
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
                "i  v                                                                           i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                             w                                i",
                "i                              c              w                                i",
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
            put('p', player);
            put('e', new Skeleton(level, 50, 1F, 200, 6, 0.2F));
            put('c', new Coffee(level));
            put('l', new PortalJungle(level, false, "Portal"));
            put('i', new InvisibleWall(level));
            put('v', new SpawnVolume(level, 30, 5, "Spawn"));
        }};
    }
}
