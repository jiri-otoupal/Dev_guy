package com.jiri.entities.items;

import com.jiri.level.Level;

public interface IItem {
    boolean use() throws Level.InvalidTemplateMap;

}
