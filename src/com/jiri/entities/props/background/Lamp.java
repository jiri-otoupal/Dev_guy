package com.jiri.entities.props.background;

import com.jiri.level.Level;

public class Lamp extends BackgroundProp {

    public Lamp(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {' ', ' ', '_', '_', '_', '_', ' '},
                {' ', '/', ' ', '_', '_', '_', '\\'},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', ' ', ' ', ' ', ' '},
                {'|', ' ', '|', '_', ' ', ' ', ' '},
                {'_', '_', '_', '_', '|', ' ', ' '},
        };
    }
}
