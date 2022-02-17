package com.jiri.entities;

import com.jiri.level.Level;

public class Skeleton extends Enemy {
    public Skeleton(Level currentLevel, int health, float speed, long fireRate, float jumpHeight, float gravity) {
        super(currentLevel, health, speed, fireRate, jumpHeight, gravity);
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

        selectedAnimationFrames = animationState[0];
    }
}
