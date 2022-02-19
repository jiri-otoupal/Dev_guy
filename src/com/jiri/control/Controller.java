package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.entities.Player;

public class Controller {

    public Player controlledAliveEntity;

    public Controller() {

    }

    public void invokeActionFromKey(KeyStroke key) {
        if (key.getCharacter() == 'w') {
            controlledAliveEntity.Jump();
        } else if (key.getCharacter() == 'a') {
            controlledAliveEntity.MoveLeft();
        } else if (key.getCharacter() == 's') {
            controlledAliveEntity.Crouch();
        } else if (key.getCharacter() == 'd') {
            controlledAliveEntity.MoveRight();
        } else if (key.getCharacter() == ' ') {
            controlledAliveEntity.Shoot();
        }
    }
}
