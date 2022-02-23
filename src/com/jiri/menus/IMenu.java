package com.jiri.menus;

import com.jiri.entities.textrender.MenuItemText;

import java.util.List;

public interface IMenu {
    void choose();

    List<MenuItemText> getMenuItems();

    String getSelectedValue();
}
