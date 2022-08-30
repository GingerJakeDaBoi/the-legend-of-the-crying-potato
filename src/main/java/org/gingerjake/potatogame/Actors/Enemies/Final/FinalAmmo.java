package org.gingerjake.potatogame.Actors.Enemies.Final;

import org.gingerjake.potatogame.GamePanel;

public class FinalAmmo {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 2;
    public static int health = 8;
    public static int randomX = (int) (Math.random() * GamePanel.width);
    public static int randomY = (int) (Math.random() * GamePanel.height);
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        FinalAmmo.x = x;
        FinalAmmo.y = y;
        FinalAmmo.width = width;
        FinalAmmo.height = height;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        x = FinalBoss.x;
        y = FinalBoss.y;
        while (enabled) {
            if (FinalBoss.phase == 0) {

                if (x != 0) {
                    x -= speed;
                }

                if (x <= 0) {
                    enabled = false;
                    Thread.currentThread().interrupt();
                }
                if (y == 0 || y == GamePanel.height - height || y == randomY) {
                    enabled = false;
                    Thread.currentThread().interrupt();
                }

                try {
                    Thread.sleep(GamePanel.TARGET_TIME / speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}