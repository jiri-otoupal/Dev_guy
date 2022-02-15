package com.jiri.level;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.jiri.entities.EmptySpace;
import com.jiri.entities.Entity;
import com.jiri.entities.Ground;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LevelStreamer extends Level {
    DefaultTerminalFactory defaultTerminalFactory;
    public Terminal terminal;


    public LevelStreamer() throws IOException {

        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory(System.out, System.in,
                StandardCharsets.UTF_8);
        terminal = defaultTerminalFactory.createTerminal();
        TerminalSize terminalSize = terminal.getTerminalSize();
        this.width = terminalSize.getColumns();
        this.height = terminalSize.getRows();
        this.map = new Entity[height][width];
        terminal.setCursorVisible(false);
        this.clear();
        this.fillGround();
    }

    public void clear() throws IOException {
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace());
        }
    }

    public void assign(int x, int y, Entity value) {
        this.map[y][x] = value;
    }

    public void loadLevel(Level level) {
        if (level.width != width || level.height != height) {
            System.out.printf("Inconsistent Level dimensions Width %s!=%s Height %s!=%s%n", level.width, width, level.height, height);
        }
        this.map = level.map;
    }

    protected void fillGround() {
        Arrays.fill(this.map[height - 2], new Ground());
    }

    public void render() throws IOException {
        terminal.clearScreen();
        for (int line = 0; line < this.height - 1; line++) {
            for (int column = 0; column < this.width; column++) {
                this.map[line][column].render(this.map, new Point(column, line)); //Makes object translate itself to projection screen before being drawn
                terminal.putCharacter(this.map[line][column].getChar());
            }
            terminal.putCharacter('\n');
        }
        terminal.flush();
    }
}