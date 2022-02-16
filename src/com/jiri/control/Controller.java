package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.entities.Player;

public class Controller {

    public Player controlledPlayer;

    public Controller() {

    }

    public void invokeActionFromKey(KeyStroke key) {
        if (key.getCharacter() == 'w') {
            controlledPlayer.Jump();
        } else if (key.getCharacter() == 'a') {
            controlledPlayer.MoveLeft();
        } else if (key.getCharacter() == 's') {
            controlledPlayer.Crouch();
        } else if (key.getCharacter() == 'd') {
            controlledPlayer.MoveRight();
        } else if (key.getCharacter() == ' ') {
            controlledPlayer.Shoot();
        }
    }
}
