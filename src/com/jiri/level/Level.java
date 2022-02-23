package com.jiri.level;

import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.Entity1D;
import com.jiri.entities.persistent.Ground;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

public abstract class Level implements ILevel {
    public String name;
    public int width;
    public int height;
    public Entity1D[][] map;
    public Streamer streamer;
    protected String[] mapToTranslate;
    protected Map<Character, Entity1D> linker;
    protected boolean doGroundFilling = true;


    public Level(String name, int width, int height, Streamer streamer) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.streamer = streamer;
        this.init();
    }


    public void init() {
        this.map = new Entity1D[this.height][this.width];
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace(this));
        }
    }

    protected void fillGround() {
        Arrays.fill(this.map[height - 2], new Ground(this));
    }

    public Object createInstanceOfClass(String className, Object... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> classTemp = Class.forName(className);
        return classTemp.getDeclaredConstructor(Level.class, float.class).newInstance(args);
    }


    void compileMap() throws InvalidTemplateMap {
        initializeLinker();
        if (this.map.length != this.mapToTranslate.length || this.map[0].length != this.mapToTranslate[0].toCharArray().length)
            throw new InvalidTemplateMap("Dimensions of Translate Map are different from Object map");
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[y].length; x++) {
                Entity1D obj = linker.get(this.mapToTranslate[y].toCharArray()[x]);
                this.map[y][x] = obj;
            }
        }
        if (doGroundFilling)
            fillGround();
    }

    public static class InvalidTemplateMap extends Exception {
        public InvalidTemplateMap(String errorMessage) {
            super(errorMessage);
        }
    }
}

