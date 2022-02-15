package com.jiri.entities;

public class EmptySpace extends Entity {
    public EmptySpace() {
        representMap = new char[1][1];
        representMap[0][0] = ' ';
        persistent = false;
    }

}
