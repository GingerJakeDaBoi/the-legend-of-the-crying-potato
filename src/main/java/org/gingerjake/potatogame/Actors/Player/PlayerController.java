package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;

import javax.swing.*;
import java.awt.*;

public class PlayerController {
    public static final Image playerRight = new ImageIcon("Assets/Potato/NewMainR.png").getImage();
    public static final Image playerLeft = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int health = 3;
    public static int speed = 5;
    public static boolean heartGiven;
    public static boolean speedGiven;
    public static boolean powerGiven;
    public static boolean hurting;
    public static boolean enabled;
    public static boolean uping;
    public static boolean downing;
    public static boolean lefting;
    public static boolean righting;
    private static String weapon;

    public static void hurt() {
        if (!hurting) {
            health -= 1;
            hurting = true;

            //if the player was hurt, they can't be hurt again for 1 second
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    hurting = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public static void spawn(int x, int y, int width, int height, int speed, String weapon) {
        PlayerController.x = x;
        PlayerController.y = y;
        PlayerController.width = width;
        PlayerController.height = height;
        PlayerController.speed = speed;
        PlayerController.weapon = weapon;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static void tick() {
        if (downing) {
            y += speed;
        }
        if (uping) {
            y -= speed;
        }
        if (righting) {
            x += speed;
        }
        if (lefting) {
            x -= speed;
        }

        if (weapon.equals("Fist")) {
            Fist.tick();
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
            speed += 3;
            speedGiven = true;
        }
    }

    public static void giveDamage() {
        if (weapon.equals("fist")) {
            Fist.powerUp();
        }
    }


}