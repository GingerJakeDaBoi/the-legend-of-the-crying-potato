package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Enemies.GenericEnemy;

import javax.swing.*;
import java.awt.*;

public class Player {

    public static Image PotatoAsset = new ImageIcon("Assets/Dummy/Green.png").getImage();
    public static int X = 1200;
    public static int Y = 800;
    public static int width = 128;
    public static int height = 128;
    public static int health = 3;
    public static int speed = 5;
    public static boolean hurting;
    public static String currentHurt;

    public static boolean lefting;
    public static boolean righting;
    public static boolean uping;
    public static boolean downing;

    public static int pointsEarned;
    @SuppressWarnings("BusyWait")
    public static void moveUp() {
        while(uping) {
            Y -= speed;
            System.out.println("Player up");
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveDown() {
        while (downing) {
            Y += speed;
            System.out.println("Player down");
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveLeft() {
        while (lefting) {
            X -= speed;
            System.out.println("Player left");
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveRight() {
        while(righting) {
            X += speed;
            System.out.println("Player right");
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void damage() {
        hurting = true;
        if(currentHurt.equals("GenericEnemy")) {
           health -= GenericEnemy.damage;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hurting = false;
        Thread.currentThread().interrupt();
    }
}
