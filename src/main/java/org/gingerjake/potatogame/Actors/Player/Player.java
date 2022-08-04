package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.PotatoBoss;

import javax.swing.*;
import java.awt.*;

public class Player {

    public static Image PotatoAsset = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();
    public static int X = 1200;
    public static int Y = 800;
    public static int health = 3;
    public static int speed = 1;
    public static boolean hurting;

    public static boolean lefting;
    public static boolean righting;
    public static boolean uping;
    public static boolean downing;

    @SuppressWarnings("BusyWait")
    public static void moveUp() {
        while(uping) {
            Y -= speed;
            if(Y < 0) {
                Y = 0;
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveDown() {
        while (downing) {
            System.out.println("Downing");
            Y += speed;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveLeft() {
        while (lefting) {
            System.out.println("Lefting");
            X -= speed;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public static void moveRight() {
        while(righting) {
            System.out.println("Righting");
            X += speed;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void damage() {
        hurting = true;
        health -= PotatoBoss.damage;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hurting = false;
        Thread.currentThread().interrupt();
    }
}
