package com.jiri.menus;

import com.jiri.control.PlayerPawnController;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.CheckerTable;
import com.jiri.level.Level;

import java.util.ArrayList;
import java.util.List;

public class DiedMenu extends Menu {

    public DiedMenu(Level currentLevel) {
        super(currentLevel);
    }

    @Override
    public List<MenuItemText> getMenuItems() {
        ArrayList<MenuItemText> items = new ArrayList<>();
        items.add(new MenuItemText(level, "Start New Game", "start"));
        items.add(new MenuItemText(level, "Load Game", "load"));
        items.add(new MenuItemText(level, "Exit", "exit"));
        setMenuItems(items);
        return items;
    }


    @Override
    public void choose() {
        String value = getSelectedValue();
        switch (value) {
            case "start":
                try {
                    this.level.streamer.controller = new PlayerPawnController();
                    CheckerTable levelCheckerTable = new CheckerTable(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
                    this.level.streamer.loadLevel(levelCheckerTable);
                } catch (Level.InvalidTemplateMap e) {
                    e.printStackTrace();
                }
                break;
            case "exit":
                System.exit(0);
        }
    }
}
