package com.jiri.entities;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.entities.items.Item;
import com.jiri.entities.textrender.StaticText;
import com.jiri.level.DeadMenuLevel;
import com.jiri.level.Level;
import com.jiri.level.Streamer;

import java.awt.*;

public class PlayerStone extends AliveEntity {
    protected StaticText displayedHealth;

    public PlayerStone(Level currentLevel, int health, float speed, long fireRateMs) {
        super(currentLevel, health, speed, fireRateMs, 6, 0.15F);
        this.absPosition = null;
        this.frameDurationMs = 50;
        this.loops = true;
        animationState = new char[][][][]{
                {//Idle
                        {
                                {'O'},
                        },
                },
        };
        selectedAnimationFrames = animationState[0];

    }

    public PlayerStone(Level currentLevel, PlayerStone playerStone) {
        this(currentLevel, (int) playerStone.health, playerStone.speed, playerStone.fireRate);
    }

    @Override
    public void die() {
        Streamer streamer = currentLevel.streamer;
        try {
            streamer.loadLevel(new DeadMenuLevel(streamer.width, streamer.height, streamer));
        } catch (Level.InvalidTemplateMap e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertItemToBackpack(Item item) {
        return false;
    }

    @Override
    public boolean canGrabItem() {
        return true;
    }

    @Override
    public boolean applyDamage(float damage) {
        this.health -= damage;
        if (this.health > 0) {
            if (displayedHealth != null)
                displayedHealth.erase();

            displayedHealth = new StaticText(this.currentLevel, "Health " + this.health, 1000);
            displayedHealth.spawn(new Point(2, 2));
            return true;
        }
        die();
        return false;
    }


    @Override
    public int resolveAnimationState() {
        //Idle State if none of previous
        return 0;
    }

    @Override
    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
        if (this.currentLevel.streamer != null)
            this.currentLevel.streamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
