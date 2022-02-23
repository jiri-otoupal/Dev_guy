package com.jiri.level;

import com.jiri.control.MenuController;
import com.jiri.menus.Menu;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.textrender.LogoText;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.menus.StartMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenu extends Level {
    protected List<MenuItemText> menuItemList;
    public Menu menu;

    public MainMenu(int width, int height, Streamer streamer) throws Level.InvalidTemplateMap {
        super("Test Level", width, height, streamer);
        this.doGroundFilling = false;
        this.mapToTranslate = new String[]{
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "        n                                                                       ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "        s                                                                       ",
                "                                                                                ",
                "        l                                                                       ",
                "                                                                                ",
                "        e                                                                       ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
                "                                                                                ",
        };
    }

    @Override
    public void initializeLinker() {
        Level level = this;
        menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItemText(level, "Start Game", "start"));
        menuItemList.add(new MenuItemText(level, "Load Game", "load"));
        menuItemList.add(new MenuItemText(level, "Exit", "exit"));

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('n', new LogoText(level));
            put('s', menuItemList.get(0));
            put('l', menuItemList.get(1));
            put('e', menuItemList.get(2));
        }};
        menu = new StartMenu(level, menuItemList);
        this.streamer.controller = new MenuController(menu);
    }
}
