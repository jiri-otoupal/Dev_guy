package com.jiri.level;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.jiri.control.Controller;
import com.jiri.entities.EmptySpace;
import com.jiri.entities.Entity1D;
import com.jiri.entities.IEntity;


import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

public class LevelStreamer extends Level  {
    DefaultTerminalFactory defaultTerminalFactory;
    public Terminal terminal;
    public Level loadedLevel = null;
    public Controller playerController;
    private final List<IEntity> listeners;
    private final int target_fps;

    public LevelStreamer(Controller controller, int target_fps) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory(System.out, System.in,
                StandardCharsets.UTF_8);
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(80, 24));
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

    public void removeListener(IEntity toRemove) {
        listeners.remove(toRemove);
    }

    public void broadcastTick(long elapsedMs) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i < listeners.size(); i++) { // Needs to be in this for because of removing during iterations
            IEntity listener = listeners.get(i);
            if (listener.getLifeSpan() < 0) {
                listener.erase();
                break;
            }
            listener.tickEvent(elapsedMs);
        }

    }

    public boolean assignAt(Point coords, Entity1D value) {
        if (coords != null) {
            this.map[coords.y][coords.x] = value;
            return true;
        }
        return false;
    }

    public boolean isValidLocation(Point location) {
        return location.x >= 0 && location.x < this.width && location.y >= 0 && location.y < this.height;
    }

    public Entity1D getInstanceAt(Point coords) {
        return this.map[coords.y][coords.x];
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


    public void render() throws IOException, InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        long frame_start = System.currentTimeMillis();
        terminal.clearScreen();
        for (int line = 0; line < this.height - 1; line++) { //-1 because of ground
            for (int column = 0; column < this.width; column++) {
                Entity1D obj = this.map[line][column];
                obj.useLight();
                if (obj.absPosition == null)
                    obj.absPosition = new Point(column, line);
                obj.render(this.map, new Point(column, line));
                //Makes object translate itself to projection screen before being drawn
                // Adds rendered body positions
                terminal.putCharacter(this.map[line][column].getChar());

            }
            terminal.putCharacter('\n');
        }
        terminal.flush();
        long frame_time = System.currentTimeMillis() - frame_start;
        this.broadcastTick(frame_time);
        Thread.sleep(1000 / (target_fps * (frame_time + 1)));
    }
}