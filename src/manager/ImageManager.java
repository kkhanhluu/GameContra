package manager;

import javax.swing.*;

import gameObjects.GameObject;

import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class ImageManager {

    private ImageManager() {
    }

    public static Image getImage(String imgName) {
        return new ImageIcon("F:\\Informatik\\Java\\Java Workspace\\Contra\\src\\res\\images\\" + imgName).getImage();
    }

    public static int getWidthImage(int type, int orientAction) {
        if (type == GameObject.CANNON || type == GameObject.WALL_TURRET) {
            return 80;
        }
        int res = 0;
        switch (orientAction) {
            case GameObject.UP_RIGHT: {
                if (type == GameObject.BILL) res = 40;
                break;
            }
            case GameObject.LEFT_UP: {
                if (type == GameObject.BILL) res = 40;
                break;
            }

            case GameObject.JUMP_RIGHT: {
                if (type == GameObject.BILL) res = 45;
                break;
            }
            case GameObject.JUMP_LEFT: {
                if (type == GameObject.BILL) res = 45;
                break;
            }

            case GameObject.DIAGONAL_UP_RIGHT: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SNIPER) res = 56;
                break;
            }
            case GameObject.RUN_RIGHT: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SOLDIER) res = 45;
                break;
            }
            case GameObject.DIAGONAL_RIGHT_DOWN: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SNIPER) res = 56;
                break;
            }
            case GameObject.DIAGONAL_DOWN_LEFT: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SNIPER) res = 56;
                break;
            }
            case GameObject.RUN_LEFT: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SOLDIER) res = 45;
                break;
            }
            case GameObject.DIAGONAL_LEFT_UP: {
                if (type == GameObject.BILL) res = 56;
                if (type == GameObject.SNIPER) res = 56;
                break;
            }

            case GameObject.FIRE_RIGHT: {
                if (type == GameObject.BILL) res = 68;
                if (type == GameObject.SOLDIER) res = 60;
                if (type == GameObject.SNIPER) res = 60;
                break;
            }
            case GameObject.FIRE_LEFT: {
                if (type == GameObject.BILL) res = 68;
                if (type == GameObject.SOLDIER) res = 60;
                if (type == GameObject.SNIPER) res = 60;
                break;
            }

            case GameObject.RIGHT_DOWN: {
                if (type == GameObject.BILL) res = 90;
                if (type == GameObject.SOLDIER) res = 90;
                break;
            }
            case GameObject.DOWN_LEFT: {
                if (type == GameObject.BILL) res = 90;
                if (type == GameObject.SOLDIER) res = 90;
                break;
            }
            default:
        }
        return res;
    }

    public static int getHeightImage(int type, int orientAction) {
        if (type == GameObject.WALL_TURRET || type == GameObject.CANNON) {
            return 80;
        }

        int res = 0;
        switch (orientAction) {
            case GameObject.UP_RIGHT: {
                if (type == GameObject.BILL) res = 100;
                break;
            }
            case GameObject.LEFT_UP: {
                if (type == GameObject.BILL) res = 100;
                break;
            }

            case GameObject.DIAGONAL_UP_RIGHT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SNIPER) res = 75;
                break;
            }
            case GameObject.RUN_RIGHT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SOLDIER) res = 68;
                break;
            }
            case GameObject.FIRE_RIGHT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SOLDIER) res = 70;
                if (type == GameObject.SNIPER) res = 70;
                break;
            }
            case GameObject.DIAGONAL_RIGHT_DOWN: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SNIPER) res = 70;
                break;
            }
            case GameObject.DIAGONAL_DOWN_LEFT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SNIPER) res = 70;
                break;
            }
            case GameObject.RUN_LEFT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SOLDIER) res = 68;
                break;
            }
            case GameObject.FIRE_LEFT: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SOLDIER) res = 70;
                if (type == GameObject.SNIPER) res = 70;
                break;
            }
            case GameObject.DIAGONAL_LEFT_UP: {
                if (type == GameObject.BILL) res = 72;
                if (type == GameObject.SNIPER) res = 75;
                break;
            }

            case GameObject.RIGHT_DOWN: {
                if (type == GameObject.BILL) res = 38;
                if (type == GameObject.SOLDIER) res = 38;
                break;
            }
            case GameObject.DOWN_LEFT: {
                if (type == GameObject.BILL) res = 38;
                if (type == GameObject.SOLDIER) res = 38;
                break;
            }
            case GameObject.JUMP_RIGHT: {
                if (type == GameObject.BILL) res = 45;
                break;
            }
            case GameObject.JUMP_LEFT: {
                if (type == GameObject.BILL) res = 45;
                break;
            }
            default:
        }
        return res;
    }
}
