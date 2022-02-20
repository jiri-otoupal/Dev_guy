package com.jiri.fonts;

public class Font {
    protected char[][][] numbers;
    protected char[][][] letters;

    public char[][] getNumber(int number) {
        return numbers[number];
    }

    public char[][] getLetter(char letter) {
        return letters[letter - 97];
    }

}
