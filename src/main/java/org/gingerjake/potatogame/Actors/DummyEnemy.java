package org.gingerjake.potatogame.Actors;

import javax.swing.*;
import java.awt.*;

public class DummyEnemy {
    public static boolean visible = false;
    public static float X;
    public static float Y;
    public static float HitboxX;
    public static float HitboxY;
    public static int health = 3;
    public static int speed = 1000;
    public static int damage = 1;
    public static Image enemyAsset = new ImageIcon("Assets/Dummy/Enemy.png").getImage();

    public static void spawn(int X, int Y) {
        visible = true;
        health = 3;
        speed = 1;
        damage = 1;
        DummyEnemy.X = X;
        DummyEnemy.Y = Y;
        HitboxX = X + 20;
        HitboxY = Y + 20;

    }

    public static void hurt() {
        //do damage on a delay
        for(int i = 0; i < damage; i++) {
            Potato.health--;
        }
    }





}
