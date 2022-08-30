package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Enemies.Final.FinalAmmo;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class Chaser {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 2;
    public static int health = 8;
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        org.gingerjake.potatogame.Actors.Enemies.Chaser.x = x;
        org.gingerjake.potatogame.Actors.Enemies.Chaser.y = y;
        org.gingerjake.potatogame.Actors.Enemies.Chaser.width = width;
        org.gingerjake.potatogame.Actors.Enemies.Chaser.height = height;
    }

    public static void enable() {
        enabled = true;
    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        while (enabled) {
            if (PlayerController.x + PlayerController.width / 2 > x) {
                x += speed;
            }
            if (PlayerController.x + PlayerController.width / 2 < x) {
                x -= speed;
            }
            if (PlayerController.y + PlayerController.height / 2 > y) {
                y += speed;
            }
            if (PlayerController.y + PlayerController.height / 2 < y) {
                y -= speed;
            }

            //if either PlayerController or Chaser overlap, damage the player.
            FinalAmmo.hit(x, width, y, height);

            //if either Fist or Chaser overlap, damage the chaser.
            if (Fist.x + Fist.width > x && Fist.x < x + width && Fist.y + Fist.height > y && Fist.y < y + height) {
                if(Fist.visible) {
                    health -= Fist.power;
                    Fist.visible = false;
                }
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(health <= 0) {
                enabled = false;
                Thread.currentThread().interrupt();
            }
        }
        Thread.currentThread().interrupt();


    }

}

