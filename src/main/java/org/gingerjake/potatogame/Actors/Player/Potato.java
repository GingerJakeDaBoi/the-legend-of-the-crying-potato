package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Potato {

    public static Image PotatoAsset = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();
    public static int X = GamePanel.width / 2;
    public static int Y = GamePanel.height / 2;
    public static int health = 3;

    public static void moveUp() {
        Y -= 10;
    }
    public static void moveDown() {
        Y += 10;
    }
    public static void moveLeft() {
        X -= 10;
    }
    public static void moveRight() {
        X += 10;
    }

}
