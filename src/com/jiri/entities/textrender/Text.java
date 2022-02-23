package com.jiri.entities.textrender;

import com.jiri.entities.Animated;
import com.jiri.entities.Entity1D;
import com.jiri.entities.Player;
import com.jiri.level.Level;

import java.awt.Point;

public abstract class Text extends Animated implements IText {
    public Entity1D owner;

    public Text(Level currentLevel, boolean enableGravity, float lifeSpan) {
        super(currentLevel, enableGravity, lifeSpan);
    }

    public Text(Level currentLevel, boolean enableGravity, float lifeSpan, Entity1D owner) {
        super(currentLevel, enableGravity, lifeSpan);
        this.owner = owner;
    }

    public void spawnAtPlayer() {
        Player player = currentLevel.streamer.controller.controlledAliveEntity;
        Point renderPoint = player.textRenderPoint;
        if (currentLevel.streamer.getInstanceAt(renderPoint).lifeSpan <= 0)
            currentLevel.streamer.assignAt(new Point(renderPoint.x, renderPoint.y - 1), this);
        owner = player;
        owner.currentRenderedText = this;
    }

    public void spawn(Point location) {
        if (owner == null && currentLevel.streamer.getInstanceAt(location).lifeSpan <= 0)
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
        else if (owner != null && owner.currentRenderedText != null && owner.currentRenderedText.lifeSpan <= 0) {
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        } else if (owner != null && owner.currentRenderedText == null) {
            currentLevel.streamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        }
    }
}
