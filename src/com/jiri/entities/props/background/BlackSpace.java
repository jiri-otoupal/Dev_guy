package com.jiri.entities.props.background;

import com.jiri.level.Level;

public class BlackSpace extends BackgroundProp{
    public BlackSpace(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {'▦'},
        };
    }
}
