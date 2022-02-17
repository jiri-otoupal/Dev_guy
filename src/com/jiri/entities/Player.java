package com.jiri.entities;

import com.jiri.level.Level;

public class Player extends AliveEntity {

    public Player(Level currentLevel, int health, float speed, long fireRateMs) {
        super(currentLevel, health, speed, fireRateMs, 6, 0.2F);
        this.currentLevel.levelStreamer.playerController.controlledPlayer = this;
        this.elapsedMsToFrame = 50;
        this.loops = true;
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },
                {//Falling
                        {
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {'.', '¨', '.'},
                                {'¨', ' ', '¨'}
                        },
                        {
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {'/', '¨', '\\'},
                                {'¨', ' ', '¨'}
                        },
                },
                {//Moving
                        {
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'(', ' ', ')'}
                        }
                },
                {//Shooting
                        {
                                {' ', 'O', ' '},
                                {'c', '|', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        },
                        {
                                {' ', 'o', ' '},
                                {'c', 'I', 'c'},
                                {' ', '*', ' '},
                                {'/', ' ', '\\'}
                        }
                },

        };

        usedAnimationFrames = animationState[0];

    }

    @Override
    public int resolveAnimationState() {
        if (falling) {
            return 1;
        } else if (moving) {
            return 2;
        } else if (shooting) {
            return 3;
        }
        //Idle State
        return 0;
    }
}
