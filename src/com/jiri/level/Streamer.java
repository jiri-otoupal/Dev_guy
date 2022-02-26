package com.jiri.level;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.jiri.control.Controller;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.Entity1D;
import com.jiri.entities.IEntity;
import com.jiri.entities.props.background.BackgroundProp;
import com.jiri.saves.SaveOperator;
import org.jetbrains.annotations.NotNull;


import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Streamer {
    public Terminal terminal;
    private Level loadedLevel = null;
    public Controller controller;
    private final List<IEntity> listeners;
    private final int target_fps;
    public int width;
    public int height;
    public Entity1D[][] map;

    public Streamer(Controller controller, int target_fps) throws IOException {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory(System.out, System.in,
                StandardCharsets.UTF_8);
        defaultTerminalFactory.setInitialTerminalSize(new TerminalSize(80, 24));
        this.controller = controller;
        this.target_fps = target_fps;
        terminal = defaultTerminalFactory.createTerminal();
        TerminalSize terminalSize = terminal.getTerminalSize();
        this.width = terminalSize.getColumns();
        this.height = terminalSize.getRows();
        this.map = new Entity1D[height][width];
        this.listeners = new ArrayList<>();
        terminal.setCursorVisible(false);
        this.clear();
        System.out.println(terminalSize);
    }

    public void clear() {
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace(this.loadedLevel));
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
                continue;
            }
            listener.tickEvent(elapsedMs);
        }

    }

    public boolean assignAt(Point coords, Entity1D value) {
        if (coords != null && isValidLocation(coords)) {
            this.map[coords.y][coords.x] = value;
            return true;
        }
        if (coords != null)
            System.out.println("Could not Assign to " + coords);
        return false;
    }

    public boolean isValidLocation(Point location) {
        return location != null && location.x >= 0 && location.x < this.width && location.y >= 0 && location.y < this.height;
    }

    public Entity1D getInstanceAt(@NotNull Point coords) {
        return this.map[coords.y][coords.x];
    }

    /**
     * @param level Level to load
     *              <p>
     *              This method sets pointer of current level to streamer to NULL !
     */
    public void loadLevel(@NotNull Level level) {
        if (level.width != width || level.height != height) {
            System.out.printf("Inconsistent Level dimensions Width %s!=%s Height %s!=%s%n", level.width, width, level.height, height);
        }
        if (level.player != null)
            SaveOperator.saveGame("save.xml", level.player);
        // Clear Streamer ref from previous level
        if (this.loadedLevel != null) {
            this.listeners.clear();
            this.loadedLevel.streamer = null;
        }
        //Set ref to new level
        this.map = level.map;
        this.loadedLevel = level;
        try {
            this.loadedLevel.compileMap();
        } catch (Level.InvalidTemplateMap e) {
            e.printStackTrace();
        }

        System.gc();
    }


    public void render() throws IOException, InterruptedException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        long frame_start = System.currentTimeMillis();
        terminal.clearScreen();

        for (int line = 0; line < this.height; line++) { //-1 because of ground
            for (int column = 0; column < this.width; column++) {
                Entity1D obj = this.map[line][column];
                if (obj == null)
                    continue;
                Point position = new Point(column, line);
                if (obj.absPosition == null)
                    obj.absPosition = position;
                if (!obj.visible)
                    continue;
                obj.useLight();
                obj.render(this.map, position);
                // Makes object translate itself to projection screen before being drawn
                // Adds rendered body positions
                terminal.putCharacter(this.map[line][column].getChar());

            }
            terminal.putCharacter('\n');
        }

        // Background Props need to be rendered again to compensate overwrites
        for (BackgroundProp prop : this.loadedLevel.backgroundProps)
            if (prop.absPosition != null && prop.visible)
                prop.render(this.map, prop.absPosition);


        terminal.flush();
        long frame_time = System.currentTimeMillis() - frame_start;
        this.broadcastTick(frame_time);
        Thread.sleep(1000 / (target_fps * (frame_time + 1)));
    }

}