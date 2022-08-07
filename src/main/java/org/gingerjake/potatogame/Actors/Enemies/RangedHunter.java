package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class RangedHunter {
    public static Image EnemyAsset = new ImageIcon("Assets/Dummy/FarmTempIcon.png").getImage();
    public static Image EnemyAmmo = new ImageIcon("Assets/Dummy/FarmTempIcon.png").getImage();
    public static int AmmoX;
    public static int AmmoY;
    public static int AmmoXDir;
    public static int AmmoYDir;
    public static int X;
    public static int Y;
    public static int width = 128;
    public static int height = 128;
    public static boolean spawned;
    public static boolean shooting;
    public static int health = 5;
    public static int speed = 1;
    public static int damage = 2;

    public static void spawn(int x, int y) {
        X = x;
        Y = y;
        spawned = true;
        health = 5;

    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        while (spawned) {

            //pick a random place on the screen
            X = (int) (Math.random() * GamePanel.width);
            Y = (int) (Math.random() * GamePanel.height);

            if (!shooting) {
                shooting = true;
                new Thread(RangedHunter::shoot).start();
            }


            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (health <= 0) {
                spawned = false;
                Thread.currentThread().interrupt();
                System.out.println("Enemy died");

            }


        }
        Thread.currentThread().interrupt();
    }

    @SuppressWarnings("BusyWait")
    public static void shoot() {
        //shoot at the player
        AmmoX = X;
        AmmoY = Y;
        AmmoXDir = Player.X;
        AmmoYDir = Player.Y;
        shooting = true;
        while (shooting) {
            if (AmmoX < AmmoXDir) {
                AmmoX += speed;
            } else if (AmmoX > AmmoYDir) {
                AmmoX -= speed;
            }

            if (AmmoY < AmmoYDir) {
                AmmoY += speed;
            } else if (AmmoY > AmmoYDir) {
                AmmoY -= speed;
            }
            if (!Player.hurting) {
                if (AmmoX == Player.X && AmmoY == Player.Y) {
                    new Thread(Player::damage).start();
                }
            }
            try {
                Thread.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (AmmoX == AmmoXDir && AmmoY == AmmoYDir) {
                shooting = false;
                Thread.currentThread().interrupt();
            }
            if(!shooting) {
                Thread.currentThread().interrupt();
            }

        }
    }
}


