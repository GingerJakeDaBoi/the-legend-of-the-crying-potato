package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.Enemy;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Fist {
    public static boolean visible = false;
    public static String direction;
    public static Image FistR = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
    public static Image FistL = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
    public static Image FistU = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
    public static Image FistD = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();
    public static int width = 64;
    public static int height = 64;
    public static List enemyList = new List();
    public static int X;
    public static int Y;
    public static boolean FistRunning = false;

    @SuppressWarnings("BusyWait")
    public static void left() {
        visible = true;
        direction = "left";
        X = Potato.X + 20;
        Y = Potato.Y + 20;
        System.out.println("Fist left");
        FistRunning = true;
        while (visible) {
            X -= 10;
            if (X < 0) {
                visible = false;
            }

            if (Fist.X >= Enemy.X && Fist.X <= Enemy.X + Enemy.width && Fist.Y >= Enemy.Y && Fist.Y <= Enemy.Y + Enemy.height) {
                Enemy.health -= 1;
                visible = false;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
        FistRunning = false;
        visible = false;

    }

    @SuppressWarnings("BusyWait")
    public static void right() {
        visible = true;
        direction = "right";
        X = Potato.X + 20;
        Y = Potato.Y + 20;
        System.out.println("Fist right");
        FistRunning = true;
        while (visible) {
            X += 10;
            if (X > GamePanel.width) {
                visible = false;
            }
            if (Fist.X >= Enemy.X && Fist.X <= Enemy.X + Enemy.width && Fist.Y >= Enemy.Y && Fist.Y <= Enemy.Y + Enemy.height) {
                Enemy.health -= 1;
                visible = false;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
        FistRunning = false;
        visible = false;

    }

    @SuppressWarnings("BusyWait")
    public static void up() {
        visible = true;
        direction = "up";
        X = Potato.X + 20;
        Y = Potato.Y + 20;
        System.out.println("Fist up");
        FistRunning = true;
        while (visible) {
            Y -= 10;
            if (Y < 0) {
                visible = false;
            }
            if (Fist.X >= Enemy.X && Fist.X <= Enemy.X + Enemy.width && Fist.Y >= Enemy.Y && Fist.Y <= Enemy.Y + Enemy.height) {
                Enemy.health -= 1;
                visible = false;
            }


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
        FistRunning = false;
        visible = false;
    }

    @SuppressWarnings("BusyWait")
    public static void down() {
        visible = true;
        direction = "down";
        X = Potato.X + 20;
        Y = Potato.Y + 20;
        System.out.println("Fist down");
        FistRunning = true;
        while (visible) {
            Y += 10;
            if (Y > GamePanel.height) {
                visible = false;
            }
            if (Fist.X >= Enemy.X && Fist.X <= Enemy.X + Enemy.width && Fist.Y >= Enemy.Y && Fist.Y <= Enemy.Y + Enemy.height) {
                Enemy.health -= 1;
                visible = false;
            }


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.currentThread().interrupt();
        FistRunning = false;
        visible = false;
    }
}
