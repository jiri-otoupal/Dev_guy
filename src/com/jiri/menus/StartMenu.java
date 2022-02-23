package com.jiri.menus;

import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.Level;

import java.util.ArrayList;
import java.util.List;

public class StartMenu extends Menu {

    public StartMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Start Game", "start"));
        items.add(new MenuItemText(level, "Load Game", "load"));
        items.add(new MenuItemText(level, "Exit", "exit"));
        setMenuItems(items);
        return items;
    }


    @Override
    public void choose() {
        System.out.println(getSelectedValue());
    }
}
