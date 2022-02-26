package com.jiri.volumes;

import com.jiri.entities.Entity1D;
import com.jiri.entities.textrender.BannerText;
import com.jiri.level.Level;

import java.awt.*;

public class SpawnVolume extends Volume {
    public SpawnVolume(Level currentLevel, int width, int height, String name) {
        super(currentLevel, width, height, name);
    }


    @Override
    public boolean use(Entity1D instigator) {
        new BannerText(currentLevel, currentLevel.name, 300, new Point(8, 2));
        erase();
        return true;
    }
}
