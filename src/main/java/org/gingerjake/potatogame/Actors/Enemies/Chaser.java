package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class Chaser {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 1;
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        Chaser.x = x;
        Chaser.y = y;
        Chaser.width = width;
        Chaser.height = height;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        while (enabled) {
            if (PlayerController.x > x) {
                x += speed;
            }
            if (PlayerController.x < x) {
                x -= speed;
            }
            if (PlayerController.y > y) {
                y += speed;
            }
            if (PlayerController.y < y) {
                y -= speed;
            }
            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.currentThread().interrupt();
        System.out.println("Thread Interrupted");


    }

}

