package gameObjects;

import java.awt.*;

public abstract class GameObject {
    public static final int BILL = 0;
    public static final int SOLDIER = 1;
    public static final int SNIPER = 2;
    public static final int WALL_TURRET = 3;
    public static final int CANNON = 4;
    public static final int ITEM = 5;
    public static final int BOSS = 6;

    public static final int UP = 0;
    public static final int UP_RIGHT = 1;
    public static final int DIAGONAL_UP_RIGHT = 2;
    public static final int RUN_RIGHT = 3;
    public static final int FIRE_RIGHT = 4;
    public static final int DIAGONAL_RIGHT_DOWN = 5;
    public static final int RIGHT_DOWN = 6;
    public static final int DOWN = 7;
    public static final int DOWN_LEFT = 8;
    public static final int DIAGONAL_DOWN_LEFT = 9;
    public static final int RUN_LEFT = 10;
    public static final int FIRE_LEFT = 11;
    public static final int DIAGONAL_LEFT_UP = 12;
    public static final int LEFT_UP = 13;
    public static final int JUMP_RIGHT = 14;
    public static final int JUMP_LEFT = 15;
    public static final int NUMBER_ORIENT = 16;

    protected int x;
    protected int y;
    protected Image image;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public abstract void draw(Graphics2D graphics2D);
}
