package org.gingerjake.potatogame.Actors.Enemies;

import org.gingerjake.potatogame.Actors.Player.Player;

import javax.swing.*;
import java.awt.*;

public class GenericEnemy {
    public static Image EnemyAsset = new ImageIcon("Assets/Dummy/Red.png").getImage();
    public static int X;
    public static int Y;
    public static int width = 128;
    public static int height = 128;
    public static boolean spawned;
    public static int health = 30;
    public static int speed = 1;
    public static int damage = 1;

    public static void spawn(int x, int y) {
        X = x;
        Y = y;
        spawned = true;
        health = 30;

    }


    //main chase thread
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
                //if the player and enemy sprites overlap in any point, deal damage to the player
                if (X >= Player.X && X <= Player.X + Player.width && Y >= Player.Y && Y <= Player.Y + Player.height) {
                    Player.health -= damage;
                    Player.hurting = true;
                }
            }

            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //when the enemy is out of health, it is killed
            if (health <= 0) {
                spawned = false;
                Thread.currentThread().interrupt();
                System.out.println("Enemy died");

            }


        }
        Thread.currentThread().interrupt();
    }

}


