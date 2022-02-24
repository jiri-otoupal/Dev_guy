package com.jiri.entities.props.background;

import com.jiri.level.Level;

public class Computer extends BackgroundProp {

    public Computer(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {' ', ' ', '_', '_', '_', ' ', ' ', ' ', '_', ' '},
                {' ', '[', '(', '_', ')', ']', ' ', '|', '=', '|'},
                {' ', ' ', '\'', '-', '`', ' ', ' ', '|', '_', '|'},
                {' ', '/', 'm', 'm', 'm', '/', ' ', ' ', '/', ' '},
        };
    }
}
