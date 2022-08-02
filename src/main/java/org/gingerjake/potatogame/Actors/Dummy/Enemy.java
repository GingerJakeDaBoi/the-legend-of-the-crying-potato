package org.gingerjake.potatogame.Actors.Dummy;

import org.gingerjake.potatogame.Actors.Player.Potato;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    public static Image EnemyAsset = new ImageIcon("Assets/Dummy/Red.png").getImage();
    public static int X;
    public static int Y;
    public static int width = 128;
    public static int height = 128;
    public static boolean spawned;
    public static int health = 3;
    public static int speed = 10;
    public static int damage = 1;

    public static void spawn(int x, int y) {
        X = x;
        Y = y;
        spawned = true;
        health = 3;

    }

    @SuppressWarnings("BusyWait")
    public static void chase() {
        while (spawned) {

            if (X < Potato.X) {
                X += speed;
            } else if (X > Potato.X) {
                X -= speed;
            }

            if (Y < Potato.Y) {
                Y += speed;
            } else if (Y > Potato.Y) {
                Y -= speed;
            }

            if (!Potato.hurting) {
                if (X == Potato.X && Y == Potato.Y) {
                    new Thread(Potato::damage).start();
                }
            }

            try {
                Thread.sleep(50);
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


