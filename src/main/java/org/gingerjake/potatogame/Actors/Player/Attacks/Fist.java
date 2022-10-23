package org.gingerjake.potatogame.Actors.Player.Attacks;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.AudioEngine;
import org.gingerjake.potatogame.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Fist {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int power = 1;
    public static final int speed = 2;
    public static boolean visible;
    public static boolean playSound;
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
    }

    public static void tick() {
        if (direction != null) {

            if(playSound) {
                new AudioEngine("Assets/Sounds/Weapons/shootFist.wav");
                playSound = false;
            }
            if(visible) {
                switch (direction) {
                    case "left" -> {
                        System.out.println("Fist going left!");
                        x -= speed;
                    }
                    case "right" -> {
                        System.out.println("Fist going right!");
                        x += speed;
                    }
                    case "up" -> {
                        System.out.println("Fist going up!");
                        y -= speed;
                    }
                    case "down" -> {
                        System.out.println("Fist going down!");
                        y += speed;
                    }
                }
                if (x > GamePanel.width - width) {
                    x = GamePanel.width - width;
                    visible = false;
                    playSound = false;
                }
                if (x < 0) {
                    x = 0;
                    visible = false;
                    playSound = false;
                }
                if (y > GamePanel.height - height) {
                    y = GamePanel.height - height;
                    visible = false;
                    playSound = false;
                }
                if (y < 0) {
                    y = 0;
                    visible = false;
                    playSound = false;
                }
            }
        }
    }

    public static void powerUp() {
        power += 1;
    }
}
