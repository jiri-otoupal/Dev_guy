package com.jiri.level;

import com.jiri.control.MenuController;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.textrender.LogoText;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.entities.textrender.StaticText;
import com.jiri.menus.ControlsMenu;
import com.jiri.menus.Menu;
import com.jiri.menus.StartMenu;

import java.util.HashMap;
import java.util.List;

public class ControlsMenuLevel extends Level {
    public Menu menu;

    public ControlsMenuLevel(int width, int height, Streamer streamer) throws InvalidTemplateMap {
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
                "        a                                                                       ",
                "        w                                                                       ",
                "        s                                                                       ",
                "        m                                                                       ",
                "                                                                                ",
                "                                                                                ",
                "        b                                                                       ",
                "                                                                                ",
                "                                                                                ",
        };
    }

    @Override
    public void initializeLinker() {
        Level level = this;

        menu = new ControlsMenu(level);
        List<MenuItemText> menuItemList = menu.getMenuItems();

        this.linker = new HashMap<>() {{
            put(' ', new EmptySpace(level));
            put('n', new LogoText(level));
            put('a', new StaticText(level, "[A,D] to move Left and Right"));
            put('w', new StaticText(level, "[W,S] to Jump and Crouch"));
            put('s', new StaticText(level, "[Space] to shoot"));
            put('m', new StaticText(level, "[1] to use Coffee"));
            put('b', menuItemList.get(0));
        }};
        this.streamer.controller = new MenuController(menu);
    }
}
