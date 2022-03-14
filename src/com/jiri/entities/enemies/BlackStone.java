package com.jiri.entities.enemies;

import com.jiri.entities.effects.EffectHitPlayer;
import com.jiri.level.Level;

import java.awt.*;

public class BlackStone extends Enemy {
    public BlackStone(Level currentLevel) {
        super(currentLevel, 100, 1, 1, 1, 0, 30);
        damageReactions = new String[]{"Argh", "Bastard", "You Die", "Errgh", "Jabba"};
        animationState = new char[][][][]{
                {//Idle
                        {
                                {'◉'},
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
