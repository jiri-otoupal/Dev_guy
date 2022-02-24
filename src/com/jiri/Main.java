package com.jiri;


import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.control.MenuController;
import com.jiri.level.Level;
import com.jiri.level.MainMenuLevel;
import com.jiri.level.Streamer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static String saveFileName = "save.xml";

    public static void main(String[] args) throws IOException, InterruptedException, Level.InvalidTemplateMap, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int target_fps = 60;

        //PlayerPawnController playerController = new PlayerPawnController();
        Streamer streamer = new Streamer(null, target_fps);
        //Player player = SaveOperator.readXML("test.xml",streamer);
        MainMenuLevel mainMenuLevel = new MainMenuLevel(streamer.width, streamer.height, streamer);
        streamer.controller = new MenuController(mainMenuLevel.menu);

        //CompanyFight levelCompanyFight = new CompanyFight(streamer.width, streamer.height, streamer);
        streamer.loadLevel(mainMenuLevel);
        Character pressed = 'i'; // Lanterna Lib has visible property protected, watching if handle to Character is null ! needs to be initialized
        //noinspection LoopConditionNotUpdatedInsideLoop
        while (pressed != null) {
            streamer.render();
            KeyStroke key = streamer.terminal.pollInput();
            if (key != null)
                streamer.controller.invokeActionFromKey(key);
        }


    }
}
