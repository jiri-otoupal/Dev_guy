package com.jiri.entities;

import com.jiri.level.Level;

public class BannerText extends Animated {
    public BannerText(Level currentLevel, boolean enableGravity, boolean canGetOlder) {
        super(currentLevel, false);
        this.lifeSpan = 2000;

    }
}
