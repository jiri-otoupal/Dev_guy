package com.jiri.menus;

import com.jiri.Main;
import com.jiri.control.PlayerPawnController;
import com.jiri.entities.Player;
import com.jiri.entities.textrender.MenuItemText;
import com.jiri.level.CompanyFight;
import com.jiri.level.Level;
import com.jiri.saves.SaveOperator;

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
                    CompanyFight levelCompanyFight = new CompanyFight(this.level.streamer.width, this.level.streamer.height, this.level.streamer);
                    this.level.streamer.loadLevel(levelCompanyFight);
                } catch (Level.InvalidTemplateMap e) {
                    e.printStackTrace();
                }
                break;
            case "load":
                Player player = SaveOperator.loadSave(Main.saveFileName, this.level.streamer);
                if (player == null)
                    return;
                this.level.streamer.controller = new PlayerPawnController();
                this.level.streamer.player = player;
                this.level.streamer.loadLevel(player.currentLevel);
                break;
            case "exit":
                System.exit(0);
        }
    }
}
