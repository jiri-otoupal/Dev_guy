package com.jiri.entities.persistent;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

public class EmptySpace extends Entity1D {
    public EmptySpace(Level currentLevel) {
        super(currentLevel);
        representMap = new char[1][1];
        representMap[0][0] = ' ';
        persistent = false;
    }

}
