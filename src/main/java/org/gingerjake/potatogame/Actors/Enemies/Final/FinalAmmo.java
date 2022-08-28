package org.gingerjake.potatogame.Actors.Enemies.Final;

import org.gingerjake.potatogame.GamePanel;

public class FinalAmmo {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 5;
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
        randomY = (int) (Math.random() * GamePanel.height);
        randomX = (int) (Math.random() * GamePanel.width);
        while (enabled) {

            if(x < randomX) {
                x -= 1;
            } else if (x > randomX) {
                x += 1;
            }
            if(y < randomY) {
                y -= 1;
            } else if (y > randomY) {
                y += 1;
            }






            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (x == 0 || x == GamePanel.width - width || x == randomX) {
                enabled = false;
                Thread.currentThread().interrupt();
            }
            if (y == 0 || y == GamePanel.height - height || y == randomY) {
                enabled = false;
                Thread.currentThread().interrupt();
            }
        }


    }

}

