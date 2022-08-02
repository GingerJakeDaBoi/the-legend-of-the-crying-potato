package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Dummy.Enemy;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.KeyListener;
import org.gingerjake.potatogame.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Potato {

    public static Image PotatoAsset = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();
    public static int X = GamePanel.width / 2;
    public static int Y = GamePanel.height / 2;
    public static int health = 3;
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
        Y += 10;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveLeft() {
        X -= 10;
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void moveRight() {
        X += 10;
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
