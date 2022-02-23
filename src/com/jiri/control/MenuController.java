package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.jiri.menus.Menu;

public class MenuController extends Controller {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void invokeActionFromKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp || (key.getCharacter() != null && key.getCharacter() == 'w')) {
            menu.selectUp();
        } else if (key.getKeyType() == KeyType.ArrowDown || (key.getCharacter() != null && key.getCharacter() == 's')) {
            menu.selectDown();
        } else if (key.getCharacter() != null && key.getCharacter() == '\n') {
            menu.choose();
        }
    }
}
