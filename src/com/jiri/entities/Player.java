package com.jiri.entities;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.entities.items.Item;
import com.jiri.level.Level;

import java.awt.*;

public class Player extends AliveEntity {
    public Backpack backpack;

    public Player(Level currentLevel, int health, float speed, long fireRateMs) {
        super(currentLevel, health, speed, fireRateMs, 6, 0.15F);
        this.backpack = new Backpack(10);
        this.currentLevel.levelStreamer.playerController.controlledAliveEntity = this;
        this.frameDurationMs = 50;
        this.loops = true;
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'.', '¨', '.'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {'/', '¨', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'(', ' ', ')'}
                        }
                },
                {//Shooting
                        {
                                {' ', '☺', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Crouching
                        {
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        }
                },
                {//Walking + Crouching
                        {
                                {' ', ' ', ' '},
                                {' ', '☺', ' '},
                                {'c', 'I', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
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
        if (this.health > 0)
            return true;
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
        this.currentLevel.levelStreamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
