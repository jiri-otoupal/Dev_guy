package com.jiri.entities.persistent;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;


public class Ground extends Entity1D {
    public Ground(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {'█'}};
        persistent = true;
    }

}
