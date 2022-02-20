package com.jiri.entities.textrender;

import com.jiri.entities.Entity1D;
import com.jiri.level.Level;

import java.util.ArrayList;

public class DialogText extends Text implements IText {


    public DialogText(Level currentLevel, String textToDisplay, boolean loops, long wordIntervalMs, long lifeSpan) {
        super(currentLevel, false, lifeSpan);
        init(loops, wordIntervalMs, textToDisplay);
    }

    public DialogText(Level currentLevel, String textToDisplay, boolean loops, long wordIntervalMs, long lifeSpan, Entity1D owner) {
        super(currentLevel, false, lifeSpan, owner);
        init(loops, wordIntervalMs, textToDisplay);
    }


    public void init(boolean loops, long wordIntervalMs, String textToDisplay) {
        this.frameDurationMs = wordIntervalMs;
        ArrayList<ArrayList<Character>> animationStateList = new ArrayList<>();
        int i = 0;
        int maxCharacters = 0;
        String[] words = textToDisplay.split(" ");
        for (String word : words) {
            if (word.length() > maxCharacters)
                maxCharacters = word.length();
            animationStateList.add(new ArrayList<>());
            for (char c : word.toCharArray()) {
                animationStateList.get(i).add(c);
            }
            i++;
        }
        this.animationState = new char[1][words.length][1][maxCharacters];
        for (int iWordFrame = 0; iWordFrame < words.length; iWordFrame++) {

            for (int iChar = 0; iChar < words[iWordFrame].length(); iChar++) {
                this.animationState[0][iWordFrame][0][iChar] = animationStateList.get(iWordFrame).get(iChar);
            }

        }

        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
    }


}
