package com.jiri;


import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.control.MenuController;
import com.jiri.control.PlayerPawnController;
import com.jiri.level.Level;
import com.jiri.level.Fight;
import com.jiri.level.MainMenu;
import com.jiri.level.Streamer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException, Level.InvalidTemplateMap, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int target_fps = 60;
        PlayerPawnController playerController = new PlayerPawnController();
        Streamer streamer = new Streamer(playerController, target_fps);
        MainMenu mainMenu = new MainMenu(streamer.width, streamer.height, streamer);
        streamer.controller = new MenuController(mainMenu.menu);
        Fight levelFight = new Fight(streamer.width, streamer.height, streamer);
        streamer.loadLevel(mainMenu);
        Character pressed = 'i'; // Lanterna Lib has visible property protected, watching if handle to Character is null ! needs to be initialized
        while (pressed != null) {
            streamer.render();
            KeyStroke key = streamer.terminal.pollInput();
            if (key != null && (pressed = key.getCharacter()) != null)
                streamer.controller.invokeActionFromKey(key);
        }


    }
}
