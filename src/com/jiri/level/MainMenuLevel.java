package com.jiri.level;

import com.jiri.control.MenuController;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.entities.textrender.StaticText;
import com.jiri.menus.Menu;
import com.jiri.menus.StartMenu;

import java.util.HashMap;
import java.util.List;

public class MainMenuLevel extends Level {
    public Menu menu;

    public MainMenuLevel(int width, int height, Streamer streamer) throws Level.InvalidTemplateMap {
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
                "                                                                                ",
                "                                                                                ",
                "        c                                                                       ",
                "                                                                                ",
                "        e                                                                       ",
                "                                                                                ",
                "                                                                                ",
        };
    }

    @Override
    public void initializeLinker() {
        Level level = this;

        menu = new StartMenu(level);
        List<MenuItemText> menuItemList = menu.getMenuItems();

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('n', new StaticText(level,"Checkers"));
            put('s', menuItemList.get(0));
            put('c', menuItemList.get(1));
            put('e', menuItemList.get(2));
        }};
        this.streamer.controller = new MenuController(menu);
    }
}
