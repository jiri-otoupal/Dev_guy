package com.jiri.entities;

public class Ground extends Entity {
    public Ground() {
        representMap = new char[1][1];
        representMap[0][0] = '=';
        persistent = true;
    }

}
