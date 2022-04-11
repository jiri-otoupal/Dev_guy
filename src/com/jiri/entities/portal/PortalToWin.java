package com.jiri.entities.portal;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;
import com.jiri.level.Streamer;
import com.jiri.level.WinMenuLevel;

public class PortalToWin extends Portal {
    public PortalToWin(Level currentLevel, String name) {
        super(currentLevel, name);
        this.visible = false;
    }

    @Override
    public boolean use(Entity1D instigator) throws Level.InvalidTemplateMap {
        Streamer streamer = currentLevel.streamer;
        WinMenuLevel level = new WinMenuLevel(streamer.width, streamer.height, streamer);
        streamer.loadLevel(level);
        return true;
    }
}
