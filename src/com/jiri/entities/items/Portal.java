package com.jiri.entities.items;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

public abstract class Portal extends Item {
    public Portal(Level currentLevel, boolean enableGravity, String name) {
        super(currentLevel, true, name);
        this.loops = true;
        this.animationState = new char[][][][]{{
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {'○', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {'○', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', ' ', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', ' ', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', '○'}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {'○', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', '○'}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', '○'},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {'○', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', '○'},
                        {' ', '|', ' ', ' ', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', '○', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', '○'},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', ' ', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', '○'},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {'○', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', '○'},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {' ', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
                {
                        {' ', ' ', '_', '_', '_', '_', ' ', ' '},
                        {' ', '/', ' ', ' ', ' ', ' ', '\\', ' '},
                        {' ', '(', '.', ' ', ' ', ' ', ')', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', ' '},
                        {' ', '|', '.', '.', '.', ' ', '|', '○'},
                        {' ', '(', ' ', ' ', ' ', ' ', ')', ' '},
                        {'○', '\\', ' ', ' ', ' ', ' ', '/', ' '},
                        {' ', ' ', '¯', '¯', '¯', '¯', ' ', ' '}
                },
        }};
        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
        this.frameDurationMs = 10;
    }


    @Override
    public boolean grab(Entity1D instigator) throws Level.InvalidTemplateMap {
        if (instigator.canGrabItem()) {
            if (this.instant) {
                this.use();
                return true;
            }
        }
        return false;
    }
}