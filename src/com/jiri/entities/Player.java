package com.jiri.entities;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.entities.items.Item;
import com.jiri.entities.textrender.DialogText;
import com.jiri.entities.textrender.StaticText;
import com.jiri.level.DeadMenuLevel;
import com.jiri.level.Level;
import com.jiri.level.Streamer;

import java.awt.*;

public class Player extends AliveEntity {
    public Backpack backpack;
    protected StaticText displayedHealth;

    public Player(Level currentLevel, int health, float speed, long fireRateMs) {
        super(currentLevel, health, speed, fireRateMs, 6, 0.15F);
        this.backpack = new Backpack(10);
        this.currentLevel.streamer.controller.controlledAliveEntity = this;
        this.frameDurationMs = 50;
        this.loops = true;
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'.', '¨', '.'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'/', '¨', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'(', ' ', ')'}
                        }
                },
                {//Shooting
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Crouching
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        }
                },
                {//Walking + Crouching
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'(', ' ', ')'}
                        }
                },
        };
        selectedAnimationFrames = animationState[0];

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
        return this.backpack.insertItem(item);
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

            displayedHealth = new StaticText(this.currentLevel, "Health " + this.health);
            displayedHealth.spawn(new Point(2, 2));
            return true;
        }
        die();
        return false;
    }

    @Override
    public int resolveAnimationState() {
        if (falling) {
            return 1;
        } else if (moving && !crouching) {
            return 2;
        } else if (shooting) {
            return 3;
        } else if (!moving && crouching) {
            return 4;
        } else if (moving && crouching) {
            return 5;
        }
        //Idle State if none of previous
        return 0;
    }

    @Override
    public void invokeImpactEffect(Point impactLocation) { //TODO from left and right
        if (this.currentLevel.streamer != null)
            this.currentLevel.streamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
