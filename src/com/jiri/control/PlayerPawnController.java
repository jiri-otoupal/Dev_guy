package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.entities.items.Item;
import com.jiri.level.Level;

public class PlayerPawnController extends Controller {
    @Override
    public void invokeActionFromKey(KeyStroke key) {
        if (key.getCharacter() != null && key.getCharacter() == 'w') {
            controlledAliveEntity.Jump();
        } else if (key.getCharacter() != null && key.getCharacter() == 'a') {
            controlledAliveEntity.MoveLeft();
        } else if (key.getCharacter() != null && key.getCharacter() == 's') {
            controlledAliveEntity.Crouch();
        } else if (key.getCharacter() != null && key.getCharacter() == 'd') {
            controlledAliveEntity.MoveRight();
        } else if (key.getCharacter() != null && key.getCharacter() == ' ') {
            controlledAliveEntity.Shoot();
        } else if (key.getCharacter() != null && key.getCharacter() == '+') {
            try {
                Item item = controlledAliveEntity.backpack.removeItem("Coffee");
                if (item != null)
                    item.use(this.controlledAliveEntity);

            } catch (Level.InvalidTemplateMap e) {
                e.printStackTrace();
            }

        }
    }
}
