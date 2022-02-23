package com.jiri.menus;

import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.Level;

import java.util.List;

public class StartMenu extends Menu {
    public StartMenu(Level currentLevel, List<MenuItemText> menuItems) {
        super(currentLevel, menuItems);
    }

    @Override
    public void choose() {

    }
}
