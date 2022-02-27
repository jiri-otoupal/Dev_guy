package com.jiri.entities.props.interactible;

import com.jiri.entities.Movable;
import com.jiri.level.Level;

public class Crate extends Movable {
    public Crate(Level currentLevel) {
        super(currentLevel, true);
        representMap = new char[][]{
                {'+', '-', '-', '-', '-', '+'},
                {'|', '\\', ' ', ' ', '/', '|'},
                {'|', '/', '+', '+', '\\', '|'},
                {'|', '_', '_', '_', '_', '|'},
        };


    }
}
