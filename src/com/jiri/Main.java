package com.jiri;


import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.control.Controller;
import com.jiri.level.Level;
import com.jiri.level.LevelFight;
import com.jiri.level.LevelStreamer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException, Level.InvalidTemplateMap, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int target_fps = 60;
        Controller playerController = new Controller();
        LevelStreamer levelStreamer = new LevelStreamer(playerController, target_fps);
        LevelFight levelFight = new LevelFight(levelStreamer.width, levelStreamer.height, levelStreamer);
        levelStreamer.loadLevel(levelFight);
        Character pressed = 'i'; // Lanterna Lib has visible property protected, watching if handle to Character is null ! needs to be initialized

        while (pressed != null) {
            levelStreamer.render();
            KeyStroke key = levelStreamer.terminal.pollInput();

            if (key != null && (pressed = key.getCharacter()) != null)
                playerController.invokeActionFromKey(key);


        }


    }
}
