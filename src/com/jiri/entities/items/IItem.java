package com.jiri.entities.items;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

public interface IItem {
    boolean use(Entity1D instigator) throws Level.InvalidTemplateMap;

}
