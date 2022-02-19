package com.jiri.entities;

import com.jiri.level.Level;

import java.awt.*;
import java.util.ArrayList;

public class BannerText extends Animated {
    Entity1D owner;

    public BannerText(Level currentLevel, String textToDisplay, boolean loops, long wordIntervalMs, long lifeSpan) {
        super(currentLevel, false, lifeSpan);
        init(loops, wordIntervalMs, textToDisplay);

    }

    protected void init(boolean loops, long wordIntervalMs, String textToDisplay) {
        this.loops = this.loops;
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

    public BannerText(Level currentLevel, String textToDisplay, boolean loops, long wordIntervalMs, long lifeSpan, Entity1D owner) {
        super(currentLevel, false, lifeSpan);
        init(loops, wordIntervalMs, textToDisplay);
        this.owner = owner;
    }

    public void spawnAtPlayer() {
        Player player = currentLevel.levelStreamer.playerController.controlledAliveEntity;
        Point renderPoint = player.textRenderPoint;
        if (currentLevel.levelStreamer.getInstanceAt(renderPoint).lifeSpan <= 0)
            currentLevel.levelStreamer.assignAt(new Point(renderPoint.x, renderPoint.y - 1), this);
        owner = player;
        owner.currentRenderedText = this;
    }

    public void spawn(Point location) {
        if (owner == null && currentLevel.levelStreamer.getInstanceAt(location).lifeSpan <= 0)
            currentLevel.levelStreamer.assignAt(new Point(location.x, location.y - 1), this);
        else if (owner != null && owner.currentRenderedText != null && owner.currentRenderedText.lifeSpan <= 0) {
            currentLevel.levelStreamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        } else if (owner != null && owner.currentRenderedText == null) {
            currentLevel.levelStreamer.assignAt(new Point(location.x, location.y - 1), this);
            owner.currentRenderedText = this;
        }
    }
}
