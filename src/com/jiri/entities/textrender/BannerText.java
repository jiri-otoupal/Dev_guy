package com.jiri.entities.textrender;

import com.jiri.fonts.BloodFont;
import com.jiri.level.Level;

import java.awt.Point;
import java.util.ArrayList;

public class BannerText extends Text {
    ArrayList<char[][]> letters;
    ArrayList<BannerChar> bannerChars;

    public BannerText(Level currentLevel, String textToDisplay, long lifeSpan, Point location) {
        super(currentLevel, false, lifeSpan);
        if (lifeSpan == 0)
            this.canGetOlder = false;

        init(textToDisplay.toLowerCase());
        bannerChars = new ArrayList<>();
        int cursor = 0;
        for (char[][] letter : letters) {
            bannerChars.add(new BannerChar(currentLevel, letter, lifeSpan, new Point(location.x + cursor, location.y)));
            cursor += letter.length;
        }
    }

    public void init(String textToDisplay) {
        BloodFont usedFont = new BloodFont();
        letters = new ArrayList<>();
        int i = 0;
        String[] words = textToDisplay.split(" ");
        for (String word : words) {
            for (char c : word.toCharArray()) {
                letters.add(usedFont.getLetter(c));
            }
            i++;
        }
    }

    @Override
    public void init(boolean loops, long wordIntervalMs, String textToDisplay) {

    }
}
