package com.jiri.entities;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.level.Level;

import java.awt.*;

public class Skeleton extends Enemy {
    public Skeleton(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, health, speed, fireRate, jumpHeight, gravity, 30);
        damageReactions = new String[]{"Argh", "Bastard", "You Dead", "Errgh"};
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'\\', 'H', '/'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {'.', '¨', '.'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {'/', '¨', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'(', ' ', ')'}
                        }
                },
                {//Shooting
                        {
                                {' ', 'T', ' '},
                                {' ', 'O', ' '},
                                {'C', '|', 'C'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'T', ' '},
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },

        };

        selectedAnimationFrames = animationState[0];
    }

    @Override
    public void invokeImpactEffect(Point impactLocation) {
        this.currentLevel.levelStreamer.assignAt(impactLocation, new EffectHitPlayer(this.currentLevel, 40));
    }
}
