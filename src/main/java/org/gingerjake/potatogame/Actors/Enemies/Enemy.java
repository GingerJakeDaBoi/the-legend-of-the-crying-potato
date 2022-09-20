package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 1;
    public static int health = 12;
    public static boolean enabled;
    public static Image enemyAsset = new ImageIcon("Assets/Dummy/Red.png").getImage();

    public static void spawn(int x, int y, int width, int height,int speed, int health) {
        Enemy.x = x;
        Enemy.y = y;
        Enemy.width = width;
        Enemy.height = height;
        Enemy.speed = speed;
        Enemy.health = health;
        enabled = true;
        new Thread(Enemy::chase).start();
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

            //if either PlayerController or Chaser overlap, damage the player.
            if (PlayerController.x + PlayerController.width > x && PlayerController.x < x + width && PlayerController.y + PlayerController.height > y && PlayerController.y < y + height) {
                if(!PlayerController.hurting) {
                    PlayerController.hurting = true;
                    new Thread(PlayerController::hurt).start();
                    System.out.println("Hit by slow chaser");
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
                disable();
                Thread.currentThread().interrupt();
            }
        }
        Thread.currentThread().interrupt();


    }

}

