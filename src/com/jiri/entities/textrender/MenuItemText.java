package com.jiri.entities.textrender;

import com.jiri.level.Level;

import java.util.ArrayList;

public class MenuItemText extends Text {
    public boolean selected = false;
    public String value;

    public MenuItemText(Level currentLevel, String textToDisplay, String value) {
        super(currentLevel, false, 0);
        this.value = value;
        init(textToDisplay);
    }

    /**
     * Inits text for display
     * @param textToDisplay on screen
     */
    public void init(String textToDisplay) {
        ArrayList<ArrayList<Character>> animationStateList = new ArrayList<>();

        animationStateList.add(new ArrayList<>());
        for (char c : textToDisplay.toCharArray())
            animationStateList.get(0).add(c);

        int offset = 4;
        this.animationState = new char[2][1][1][textToDisplay.length() + offset];
        this.animationState[0][0][0][0] = '[';
        this.animationState[0][0][0][1] = ' ';
        this.animationState[0][0][0][2] = ']';
        this.animationState[0][0][0][3] = ' ';

        this.animationState[1][0][0][0] = '[';
        this.animationState[1][0][0][1] = '•';
        this.animationState[1][0][0][2] = ']';
        this.animationState[1][0][0][3] = ' ';

        for (int iChar = 0; iChar < textToDisplay.length(); iChar++) {
            this.animationState[0][0][0][iChar + offset] = animationStateList.get(0).get(iChar);
            this.animationState[1][0][0][iChar + offset] = animationStateList.get(0).get(iChar);
        }


        this.selectedAnimationFrames = this.animationState[this.currentAnimationState];
    }

    @Override
    public void init(boolean loops, long wordIntervalMs, String textToDisplay) {

    }

    /**
     * Decays item by milliseconds
     *
     * @param ticksMs milliseconds to decay from item
     */
    @Override
    public void decayEffectFromItems(long ticksMs) {

    }

    /**
     * Resolve animation state to be rendered
     * such as jumping animation, shooting etc.
     *
     * @return current animation state to be animated
     */
    @Override
    public int resolveAnimationState() {
        return selected ? 1 : 0;
    }
}
