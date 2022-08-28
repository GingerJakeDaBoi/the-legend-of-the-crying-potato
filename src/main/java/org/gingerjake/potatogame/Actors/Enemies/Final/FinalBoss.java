package org.gingerjake.potatogame.Actors.Enemies.Final;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.GamePanel;

public class FinalBoss {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 2;
    public static int health = 8;
    private static int randomX = (int) (Math.random() * GamePanel.width);
    private static int randomY = (int) (Math.random() * GamePanel.height);
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        FinalBoss.x = x;
        FinalBoss.y = y;
        FinalBoss.width = width;
        FinalBoss.height = height;
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

            if (x == randomX || x == 0 || x == GamePanel.width - width) {
                randomX = (int) (Math.random() * GamePanel.width);
            }

            if (y == randomY || y == 0 || y == GamePanel.height - height) {
                randomY = (int) (Math.random() * GamePanel.height);
            }


            if (x < randomX) {
                x += 1;
            } else if (x > randomX) {
                x -= 1;
            }
            if (y < randomY) {
                y += 1;
            } else if (y > randomY) {
                y -= 1;
            }

            if(!FinalAmmo.enabled) {
                FinalAmmo.spawn(x + width / 3, y + height / 3, width /2, height / 2);
                FinalAmmo.enable();
                new Thread(FinalAmmo::chase).start();
            }

            //if either Fist or FinalBoss overlap, damage the chaser.
            if (Fist.x + Fist.width > x && Fist.x < x + width && Fist.y + Fist.height > y && Fist.y < y + height) {
                if (Fist.visible) {
                    health -= Fist.power;
                    Fist.visible = false;
                }
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (health <= 0) {
                enabled = false;
                Thread.currentThread().interrupt();
            }
        }
        Thread.currentThread().interrupt();


    }

}

