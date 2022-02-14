package com.jiri;


import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static class Level {
        int width;
        int height;
        public char[][] map;
        protected Renderer levelRenderer;


        public Level(int width, int height) {
            this.width = width;
            this.height = height;
            this.map = new char[height][width];
            levelRenderer = new Renderer();
            this.clear();
        }

        public void clear() {
            for (int line = 0; line < this.height; line++) {
                Arrays.fill(this.map[line], ' ');
            }
        }

        public void assign(int x, int y, char value) {
            this.map[y][x] = value;
        }

        public void render() throws IOException {
            for (int line = 0; line < this.height - 1; line++) {
                for (int column = 0; column < this.width; column++) {
                    this.levelRenderer.cacheForOutput(this.map[line][column]);
                }
                this.levelRenderer.cacheForOutput('\n');
            }
            this.levelRenderer.output();
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader bf = new BufferedReader(
                new InputStreamReader(System.in));
        CustomReader rd = new CustomReader(bf);

        Level start = new Level(15, 10);
        start.assign(0, 0, 'a');
        start.assign(1, 1, 'a');
        start.assign(2, 2, 'a');
        int x = 0, y = 3;
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
        /*
        The DefaultTerminalFactory can be further tweaked, but we'll leave it with default settings in this tutorial.
         */

        Terminal terminal = null;

            /*
            Let the factory do its magic and figure out which implementation to use by calling createTerminal()
             */
        terminal = defaultTerminalFactory.createTerminal();

        start.render();


    }
}
