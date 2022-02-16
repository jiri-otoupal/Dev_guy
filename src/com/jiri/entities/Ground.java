package com.jiri.entities;

import com.jiri.level.Level;


public class Ground extends Entity1D {
    public Ground(Level currentLevel) {
        super(currentLevel);
        representMap = new char[1][1];
        representMap[0][0] = '=';
        persistent = true;
    }

}
