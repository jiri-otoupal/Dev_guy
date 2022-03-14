package com.jiri.level;

import com.jiri.entities.Entity1D;
import com.jiri.entities.enemies.BlackStone;
import com.jiri.entities.persistent.EmptySpace;
import com.jiri.entities.persistent.InvisibleWall;
import com.jiri.entities.props.background.BackgroundProp;
import com.jiri.entities.props.background.BlackSpace;
import com.jiri.entities.props.background.WhiteSpace;
import com.jiri.quests.Quest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Level implements ILevel {
    private int boardSize = 10;
    public String name;
    public int width;
    public int height;
    public Entity1D[][] map;
    public Streamer streamer;
    String[] mapToTranslate;
    Map<Character, Entity1D> linker;
    boolean doGroundFilling = true;

    public List<BackgroundProp> backgroundProps;
    public Quest quest;


    public Level(String name, int width, int height, Streamer streamer) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.streamer = streamer;
        this.backgroundProps = new ArrayList<>();
        this.init();
    }


    public void init() {
        this.map = new Entity1D[this.height][this.width];
        for (int line = 0; line < this.height; line++) {
            Arrays.fill(this.map[line], new EmptySpace(this));
        }
    }

    private void fillGround() {
        Arrays.fill(this.map[height - 2], new InvisibleWall(this));
    }

    private void fillBoard() {
        boolean lastWhite = false;

        for (int y = this.height / 2 - boardSize; y < this.height / 2; y++) {
            for (int x = this.width / 2 - boardSize; x < this.width / 2; x++) {
                if (!lastWhite) {
                    this.map[y][x] = new WhiteSpace(this);
                    lastWhite = true;
                } else {
                    this.map[y][x] = new BlackSpace(this);
                    lastWhite = false;
                }
            }
        }
        fillEnemyStones();
    }

    private void fillEnemyStones() {
        for (int y = this.height / 2 - 2; y < this.height / 2; y++) {
            for (int x = this.width / 2 - boardSize; x < this.width / 2; x++) {
                this.map[y][x] = new BlackStone(this);
            }
        }
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
        if (doGroundFilling) {
            fillGround();
            fillBoard();
        }
    }

    public static class InvalidTemplateMap extends Exception {
        public InvalidTemplateMap(String errorMessage) {
            super(errorMessage);
        }
    }
}

