package com.jiri.entities.props.background;

import com.jiri.level.Level;

public class WhiteSpace extends BackgroundProp{
    public WhiteSpace(Level currentLevel) {
        super(currentLevel);
        representMap = new char[][]{
                {'▢'},
        };
    }
}
