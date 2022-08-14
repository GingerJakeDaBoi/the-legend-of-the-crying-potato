package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class SlowChaser {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 1;
    public static int health = 12;
    public static boolean enabled;

    public static void spawn(int x, int y, int width, int height) {
        SlowChaser.x = x;
        SlowChaser.y = y;
        SlowChaser.width = width;
        SlowChaser.height = height;
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
            if (PlayerController.x + PlayerController.width > x && PlayerController.x < x + width && PlayerController.y + PlayerController.height > y && PlayerController.y < y + height) {
                if(!PlayerController.hurting) {
                    PlayerController.hurting = true;
                    new Thread(PlayerController::hurt).start();
                }
            }

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

