package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class PlayerController {
    public static Image playerRight = new ImageIcon("Assets/Potato/NewMainR.png").getImage();
    public static Image playerLeft = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int health = 3;
    public static int speed = 5;
    public static boolean switching;
    public static boolean heartGiven;
    public static boolean speedGiven;
    public static boolean powerGiven;
    public static boolean hurting;
    public static boolean enabled;
    public static boolean uping;
    public static boolean downing;
    public static boolean lefting;
    public static boolean righting;

    public static void hurt() { //TODO: Use in the Enemy class.
        health -= 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        hurting = false;

    }

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
        if (!switching) {
            playerRight = new ImageIcon("Assets/Potato/NewMainR.png").getImage();
        }
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

    public static void giveHeart() {
        if (!heartGiven) {
            health += 1;
            heartGiven = true;
        }
    }

    public static void giveSpeed() {
        if (!speedGiven) {
            speed += 10;
            speedGiven = true;
        }
    }

    public static void giveDamage() {
        if (!powerGiven) {
            Fist.power += 1;
            powerGiven = true;
        }
    }


}



