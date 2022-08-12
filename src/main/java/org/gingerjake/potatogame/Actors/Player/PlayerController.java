package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class PlayerController {
    public static Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    public static Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int health = 3;
    public static int speed = 10;
    public static boolean enabled;
    public static boolean uping;
    public static boolean downing;
    public static boolean lefting;
    public static boolean righting;

    public static void spawn(int x, int y, int width, int height) {
        PlayerController.x = x;
        PlayerController.y = y;
        PlayerController.width = width;
        PlayerController.height = height;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @SuppressWarnings("BusyWait")
    public static void moveUp() {
        if (enabled) {
            while (uping) {
                y -= 1;
                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveDown() {
        if (enabled) {
            while (downing) {
                y += 1;
                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveLeft() {
        if (enabled) {
            while (lefting) {
                x -= 1;
                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveRight() {
        if (enabled) {
            while (righting) {
                x += 1;
                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}



