package com.jiri.entities;

import com.jiri.level.Level;

public class Wall extends Entity1D {
    public Wall(Level currentLevel) {
        super(currentLevel);
        representMap = new char[1][1];
        representMap[0][0] = '#';
        persistent = true;
    }

}
