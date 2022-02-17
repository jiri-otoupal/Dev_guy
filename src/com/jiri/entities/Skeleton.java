package com.jiri.entities;

import com.jiri.level.Level;

public class Skeleton extends AliveEntity {

    public Skeleton(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, health, speed, fireRate, jumpHeight, gravity);
        this.elapsedMsToFrame = 50;
        this.loops = true;
        animationState = new char[][][][]{
                {//Idle
                        {
                                {' ', 'O', ' '},
                                {'\\', 'H', '/'},
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
                                {'C', '|', 'C'},
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
