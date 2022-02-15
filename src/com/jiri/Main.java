package com.jiri;


import com.googlecode.lanterna.input.KeyStroke;
import com.jiri.level.LevelStreamer;

import java.io.IOException;

public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {
        LevelStreamer start = new LevelStreamer();

        while (start.terminal!=null) {
            start.render();
            KeyStroke key = start.terminal.readInput();
            Character pressed = key.getCharacter();
            if (pressed != null && pressed != 32) {
                System.out.println(key.getCharacter());
            }else{
                Thread.sleep(50);
            }


        }

    }
}
