package com.jiri.entities.portal;

import com.jiri.entities.Entity1D;
import com.jiri.level.JavaSwamp;
import com.jiri.level.Level;
import com.jiri.level.Streamer;

public class PortalToJavaSwamp extends Portal {
    public PortalToJavaSwamp(Level currentLevel, String name) {
        super(currentLevel, name);
        this.visible = false;
    }

    @Override
    public boolean use(Entity1D instigator) throws Level.InvalidTemplateMap {
        Streamer streamer = currentLevel.streamer;
        JavaSwamp level = new JavaSwamp(streamer.width, streamer.height, streamer);
        streamer.loadLevel(level);
        return true;
    }
}
