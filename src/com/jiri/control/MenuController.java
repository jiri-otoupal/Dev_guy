package com.jiri.control;

import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.menus.Menu;

public class MenuController extends Controller {
    private final Menu menu;

    public MenuController(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void invokeActionFromKey(KeyStroke key) {
        if (key.getCharacter() == 'w') {
            menu.selectUp();
        } else if (key.getCharacter() == 's') {
            menu.selectDown();
        } else if (key.getCharacter() == '\n') {
            menu.choose();
        }
    }
}
