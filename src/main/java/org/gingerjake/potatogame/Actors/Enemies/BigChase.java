package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Player;

import javax.swing.*;
import java.awt.*;

public class BigChase {
    public static Image EnemyAsset = new ImageIcon("Assets/Dummy/FarmTempIcon.png").getImage();
    public static int X;
    public static int Y;
    public static int width = 256;
    public static int height = 256;
    public static boolean spawned;
    public static int health = 30;
    public static int speed = 1;
    public static int damage = 2;

    public static void spawn(int x, int y) {
        X = x;
        Y = y;
        spawned = true;
        health = 30;

    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        while (spawned) {

            if (X < Player.X) {
                X += speed;
            } else if (X > Player.X) {
                X -= speed;
            }

            if (Y < Player.Y) {
                Y += speed;
            } else if (Y > Player.Y) {
                Y -= speed;
            }

            if (!Player.hurting) {
                if (X == Player.X && Y == Player.Y) {
                    new Thread(Player::damage).start();
                }
            }

            try {
                Thread.sleep(5);
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

}


