package org.gingerjake.potatogame.Actors.Levels.PotatoFarm;

import javax.swing.*;
import java.awt.*;

public class SpudLauncher {
    public static Image spud = new ImageIcon("Assets/Dummy/Spud.png").getImage();
    public static Image spud2 = new ImageIcon("Assets/Dummy/Spud.png").getImage();

    public static int X;
    public static int Y;
    public static int width = 256;
    public static int height = 256;
    public static boolean spawned;
    public static int health = 5;
    public static int speed = 1;
    public static int damage = 2;

    public static void spawn(int x, int y) {
        X = x;
        Y = y;
        spawned = true;
        health = 5;

    }
}
