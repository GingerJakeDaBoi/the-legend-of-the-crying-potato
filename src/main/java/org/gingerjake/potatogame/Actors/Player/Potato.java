package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.Enemy;

import javax.swing.*;
import java.awt.*;

public class Potato {

    public static Image PotatoAsset = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();
    public static int X = 1200;
    public static int Y = 800;
    public static int health = 3;
    public static int speed = 10;
    public static boolean hurting;

    public static void moveUp() {
        //While the up key is pressed, the potato moves up
            Y -= 10;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveDown() {
        Y += speed;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveLeft() {
        X -= speed;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveRight() {
        X += speed;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void damage() {
        hurting = true;
        health -= Enemy.damage;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hurting = false;
        Thread.currentThread().interrupt();
    }
}
