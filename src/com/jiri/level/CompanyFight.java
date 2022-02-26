package com.jiri.level;

import com.jiri.entities.*;
import com.jiri.entities.enemies.Skeleton;
import com.jiri.entities.enemies.SpawnPoint;
import com.jiri.entities.items.Coffee;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.persistent.Wall;
import com.jiri.entities.portal.PortalToStreetFight;
import com.jiri.entities.props.background.Computer;
import com.jiri.entities.props.background.Window;
import com.jiri.quests.QuestCompany;
import com.jiri.volumes.SpawnVolume;

import java.util.HashMap;

public class CompanyFight extends Level {
    public CompanyFight(int width, int height, Streamer streamer) throws InvalidTemplateMap {
        super("Escape", width, height, streamer);
        this.mapToTranslate = new String[]{
                "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                 s                                            i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "i                                                                              i",
                "iwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww       wwwwwwwwi",
                "i                                                                              i",
                "i                                                                     l        i",
                "i     o                                              u                         i",
                "i                                                                              i",
                "i             p         c                                           e          i",
                "i                    k                                                         i",
                "i                                             w                                i",
                "i                                             w                                i",
                "iv                                            w                                i",
                "i                                             w                                i",
                "                                                                                ",
        };
        this.quest = new QuestCompany();
    }


    @Override
    public void initializeLinker() {
        Level level = this;
        if (streamer.player == null)
            streamer.player = new Player(level, 100, 1F, 100);
        else
            streamer.player = new Player(level, streamer.player);
        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('w', new Wall(level));
            put('o', new Window(level));
            put('u', new Window(level));
            put('k', new Computer(level));
            put('p', streamer.player);
            put('e', new Skeleton(level, 50, 1F, 200, 6, 0.2F));
            put('c', new Coffee(level));
            put('l', new PortalToStreetFight(level, "Portal"));
            put('i', new InvisibleWall(level));
            put('v', new SpawnVolume(level, 30, 1, "Spawn"));
            put('s', new SpawnPoint(level, 1000, 2));
        }};

    }
}
