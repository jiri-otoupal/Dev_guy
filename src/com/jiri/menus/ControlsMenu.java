package com.jiri.menus;

import com.jiri.control.MenuController;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.Level;
import com.jiri.level.MainMenuLevel;

import java.util.ArrayList;
import java.util.List;

public class ControlsMenu extends Menu {
    public ControlsMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public void choose() {
        String value = getSelectedValue();
        if ("back".equals(value)) {
            StartMenu controlsMenu = new StartMenu(level);
            MainMenuLevel controlsMenuLevel = null;
            try {
                controlsMenuLevel = new MainMenuLevel(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
            } catch (Level.InvalidTemplateMap e) {
                e.printStackTrace();
            }
            this.level.streamer.controller = new MenuController(controlsMenu);
            assert controlsMenuLevel != null;
            this.level.streamer.loadLevel(controlsMenuLevel);
        }
    }


    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Back", "back"));
        setMenuItems(items);
        return items;
    }
}
