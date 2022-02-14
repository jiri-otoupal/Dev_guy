package com.jiri;


public class Main {

    public static class Level{
        int width;
        int height;
        public char[][] map;


        public Level(int width,int height){
            this.width=width;
            this.height=height;
            this.map=new char[height][width];
        }

        public void assign(int x,int y,char value){
            this.map[y][x] = value;
        }

        public void render(){
            for(int line=0;line<this.height;line++){
                for(int column=0;line<this.width;column++){
                    System.out.print(this.map[line][column]);
                }
            }
        }
    }

    public static void main(String[] args) {
        // write your code here
        Level start = new Level(32,20);
        start.assign(0,0,'a');
        start.render();

    }
}
