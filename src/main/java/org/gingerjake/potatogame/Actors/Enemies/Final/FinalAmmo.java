package org.gingerjake.potatogame.Actors.Enemies.Final;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class FinalAmmo {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 2;
    public static int health = 8;
    public static boolean enabled;
    public static int direction;

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

            //if either PlayerController or Ammo overlap, damage the player.
            hit(x, width, y, height);

            if (FinalBoss.phase == 0) {
                if (x != 0) {
                    x -= speed;
                }

                if (x <= 0) {
                    enabled = false;
                }
            }
            if (FinalBoss.phase == 1 || FinalBoss.phase == 2) {
                int playerX = PlayerController.x;
                int playerY = PlayerController.y;
                if (playerX > x) {
                    x += speed;
                } else if (playerX < x) {
                    x -= speed;
                }
                if (playerY > y) {
                    y += speed;
                } else if (playerY < y) {
                    y -= speed;
                }

                if (x <= 0 || x >= GamePanel.width - width) {
                    enabled = false;
                }
                if (y <= 0 || y >= GamePanel.height - height) {
                    enabled = false;
                }

            }
            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        Thread.currentThread().interrupt();
    }

    public static void hit(int x, int width, int y, int height) {
        if (PlayerController.x + PlayerController.width > x && PlayerController.x < x + width && PlayerController.y + PlayerController.height > y && PlayerController.y < y + height) {
            if (!PlayerController.hurting) {
                PlayerController.hurting = true;
                new Thread(PlayerController::hurt).start();
            }
        }
    }
}