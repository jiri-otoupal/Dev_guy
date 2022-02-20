package com.jiri.volumes;

import com.jiri.entities.Entity1D;
import com.jiri.entities.EntityShadow2D;
import com.jiri.entities.items.Item;
import com.jiri.level.Level;

import java.awt.Point;
import java.util.Arrays;

public abstract class Volume extends Item {
    public Volume(Level currentLevel, int width, int height, String name) {
        super(currentLevel, true, name);
        this.loops = true;
        this.animationState = new char[1][1][height][width];
        for (int y = 0; y < height; y++)
            Arrays.fill(this.animationState[0][0][y], '⠀');
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 10;
    }

    @Override
    public void iterationRenderFunction(Entity1D[][] map, Point cursor, int map_x, int map_y, int ent_x, int ent_y) {
        if (map[map_y][map_x].getChar() != ' ')
            return;
        map[map_y][map_x] = new EntityShadow2D(this.currentLevel, representMap[ent_y][ent_x], this);
        bodyPositions.add(new Point(map_x, map_y));
    }

    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        if (instigator.canGrabItem())
            return this.use();

        return false;
    }
}
