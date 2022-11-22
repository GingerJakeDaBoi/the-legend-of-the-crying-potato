package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.AudioEngine;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Fist {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int power = 1;
    public static final int speed = 2;
    public static boolean visible;
    public static String direction;
    public static final Image left = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
    public static final Image right = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
    public static final Image up = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
    public static final Image down = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();

    public static void start(){
        x = PlayerController.x;
        y = PlayerController.y;
        width = 192 / 2;
        height = 192 / 2;
        Fist.visible = true;
        AudioEngine.playSound(new File("Assets/Sounds/Weapons/shootFist.wav"));
    }

    public static void tick() {
        if (direction != null) {
            if(visible) {
                switch (direction) {
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                }
                if (x > GamePanel.width - width) {
                    x = GamePanel.width - width;
                    visible = false;
                }
                if (x < 0) {
                    x = 0;
                    visible = false;
                }
                if (y > GamePanel.height - height) {
                    y = GamePanel.height - height;
                    visible = false;
                }
                if (y < 0) {
                    y = 0;
                    visible = false;
                }
            }
        }
    }

    public static void powerUp() {
        power += 1;
    }
}
