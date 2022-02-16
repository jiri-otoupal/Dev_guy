package com.jiri.level;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.jiri.control.Controller;
import com.jiri.entities.EmptySpace;
import com.jiri.entities.Entity1D;
import com.jiri.entities.IEntity;


import java.awt.Point;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class LevelStreamer extends Level {
    DefaultTerminalFactory defaultTerminalFactory;
    public Terminal terminal;
    public Level loadedLevel = null;
    public Controller playerController;
    private final List<IEntity> listeners;
    private final int target_fps;

    public LevelStreamer(Controller controller, int target_fps) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory(System.out, System.in,
                StandardCharsets.UTF_8);
        this.playerController = controller;
        this.target_fps = target_fps;
        terminal = defaultTerminalFactory.createTerminal();
        TerminalSize terminalSize = terminal.getTerminalSize();
        this.width = terminalSize.getColumns();
        this.height = terminalSize.getRows();
        this.map = new Entity1D[height][width];
        this.listeners = new ArrayList<IEntity>();
        terminal.setCursorVisible(false);
        this.clear();
        System.out.println(terminalSize);
    }

    public void clear() {
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace(this));
        }
    }

    public void addListener(IEntity toAdd) {
        listeners.add(toAdd);
    }

    public void broadcastTick(long elapsedMs) {
        for (IEntity listener : listeners)
            listener.tickEvent(elapsedMs);
    }

    public void assign(int x, int y, Entity1D value) {
        this.map[y][x] = value;
    }

    public void loadLevel(Level level) {
        if (level.width != width || level.height != height) {
            System.out.printf("Inconsistent Level dimensions Width %s!=%s Height %s!=%s%n", level.width, width, level.height, height);
        }
        // Clear Streamer ref from previous level
        if (this.loadedLevel != null)
            this.loadedLevel.levelStreamer = null;
        //Set ref to new level
        this.map = level.map;
        this.loadedLevel = level;
    }


    public void render() throws IOException, InterruptedException {
        long frame_start = System.currentTimeMillis();
        terminal.clearScreen();
        for (int line = 0; line < this.height - 1; line++) {
            for (int column = 0; column < this.width; column++) {
                Entity1D obj = this.map[line][column];
                obj.absPosition = new Point(column, line);
                obj.useLight();
                obj.render(this.map, new Point(column, line)); //Makes object translate itself to projection screen before being drawn
                terminal.putCharacter(obj.getChar());
            }
            terminal.putCharacter('\n');
        }
        terminal.flush();
        long frame_time = System.currentTimeMillis() - frame_start;
        this.broadcastTick(frame_time);
        Thread.sleep(1000 / (target_fps * (frame_time + 1)));
    }
}