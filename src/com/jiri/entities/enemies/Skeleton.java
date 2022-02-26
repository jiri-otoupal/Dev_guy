package com.jiri.entities.enemies;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.level.Level;

import java.awt.*;

public class Skeleton extends Enemy {
    public Skeleton(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, health, speed, fireRate, jumpHeight, gravity, 30);
        damageReactions = new String[]{"Argh", "Bastard", "You Die", "Errgh", "Jabba"};
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'V', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {'/', '&', '\\'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {'/', '&', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'(', ' ', ')'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Shooting
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Dying
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', 'H', 'c'},
                                {' ', '&', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', '%', 'O'},
                                {'c', '&', 'c'},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'c', 'O', '☠'},
                                {'/', '&', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'c', 'O', '☠'},
                                {'/', '&', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'c', 'O', '☠'},
                                {'/', '&', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'c', 'O', '☠'},
                                {'/', '&', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'c', 'O', '☠'},
                                {'/', '&', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'.', ' ', '☠'},
                                {'/', 'O', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'.', ' ', '☠'},
                                {'/', 'O', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'.', ' ', '☠'},
                                {'/', 'O', 'I'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {' ', ' ', ' '},
                                {'/', 'O', '☠'}
                        },
                },

        };
        this.quest_name = "skeleton";
        selectedAnimationFrames = animationState[0];
    }

    @Override
    public int resolveAnimationState() {

        if (this.health <= 0) {
            return 4;
        } else if (falling) {
            return 1;
        } else if (moving && !crouching) {
            return 2;
        }
        //Idle State if none of previous
        return 0;
    }


    @Override
    public void invokeImpactEffect(Point impactLocation) {
        this.currentLevel.streamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
