package com.jiri.entities.textrender;

import com.jiri.level.Level;

import java.util.ArrayList;

public class StaticText extends Text {
    public boolean selected = false;

    public StaticText(Level currentLevel, String textToDisplay) {
        super(currentLevel, false, 0);
        init(textToDisplay);
    }

    public StaticText(Level currentLevel, String textToDisplay, long lifeSpan) {
        super(currentLevel, false, lifeSpan);
        init(textToDisplay);
    }

    public void init(String textToDisplay) {
        ArrayList<ArrayList<Character>> animationStateList = new ArrayList<>();

        animationStateList.add(new ArrayList<>());
        for (char c : textToDisplay.toCharArray())
            animationStateList.get(0).add(c);

        int offset = 4;
        this.animationState = new char[1][1][1][textToDisplay.length() + offset];

        for (int iChar = 0; iChar < textToDisplay.length(); iChar++) {
            this.animationState[0][0][0][iChar + offset] = animationStateList.get(0).get(iChar);
        }


        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
    }

    @Override
    public void init(boolean loops, long wordIntervalMs, String textToDisplay) {

    }

}

