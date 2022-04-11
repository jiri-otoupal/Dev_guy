package com.jiri.level;

import com.jiri.control.MenuController;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.entities.textrender.StaticText;
import com.jiri.menus.DiedMenu;
import com.jiri.menus.Menu;

import java.util.HashMap;
import java.util.List;

public class WinMenuLevel extends Level {
    public Menu menu;

    public WinMenuLevel(int width, int height, Streamer streamer) throws InvalidTemplateMap {
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
                "       d                                                                        ",
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

        menu = new DiedMenu(level);
        List<MenuItemText> menuItemList = menu.getMenuItems();

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('n', new StaticText(level, "You Won"));
            put('s', menuItemList.get(0));
            put('l', menuItemList.get(1));
            put('e', menuItemList.get(2));
        }};
        this.streamer.controller = new MenuController(menu);
    }
}