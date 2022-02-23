package com.jiri.menus;

import com.jiri.entities.Entity1D;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.Level;

import java.util.List;

public abstract class Menu extends Entity1D implements IMenu {
    public List<MenuItemText> menuItems;
    public int cursor;

    public Menu(Level currentLevel, List<MenuItemText> menuItems) {
        super(currentLevel);
        this.menuItems = menuItems;
        this.cursor = 0;
        select();
    }

    public void clearSelection() {
        for (MenuItemText item : menuItems)
            item.selected = false;
    }

    public void select() {
        clearSelection();
        this.menuItems.get(this.cursor).selected = true;
    }

    public void selectDown() {
        if (cursor < this.menuItems.size() - 1) {
            this.cursor++;
            select();
        }
    }

    public void selectUp() {
        if (cursor > 0) {
            this.cursor--;
            select();
        }
    }


}
