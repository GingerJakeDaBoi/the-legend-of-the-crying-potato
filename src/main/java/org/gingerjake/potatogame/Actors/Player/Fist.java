package org.gingerjake.potatogame.Actors.Player;

import javax.swing.*;
import java.awt.*;

public class Fist {
    public static boolean visible = false;
    public static String direction;
    public static Image FistAsset;
    public static int X;
    public static int Y;

    public static void left() {
        visible = true;
        direction = "left";
        FistAsset = new ImageIcon("Assets/Fist/FistL.png").getImage();
        X = Potato.X + 20;
        Y = Potato.Y + 20;
    }

    public static void right() {
        visible = true;
        direction = "right";
        FistAsset = new ImageIcon("Assets/Fist/FistR.png").getImage();
        X = Potato.X + 20;
        Y = Potato.Y + 20;
    }

    public static void up() {
        visible = true;
        direction = "up";
        FistAsset = new ImageIcon("Assets/Fist/FistU.png").getImage();
        X = Potato.X + 20;
        Y = Potato.Y + 20;
    }

    public static void down() {
        visible = true;
        direction = "down";
        FistAsset = new ImageIcon("Assets/Fist/FistD.png").getImage();
        X = Potato.X + 20;
        Y = Potato.Y + 20;
    }
}
