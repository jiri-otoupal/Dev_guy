package com.jiri.entities.props.background;

import com.jiri.level.Level;

public class Window extends BackgroundProp {
    public Window(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]
                {
                        {' ', '_', '_', '_', ' '},
                        {'/', ' ', ' ', ' ', '\\'},
                        {'|', ' ', ' ', ' ', '|'},
                        {'\\', '_', '_', '_', '/'},
                };

    }


}
