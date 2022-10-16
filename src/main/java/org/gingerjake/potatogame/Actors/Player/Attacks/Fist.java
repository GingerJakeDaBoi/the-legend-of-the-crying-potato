package org.gingerjake.potatogame.Actors.Player.Attacks;

import org.gingerjake.potatogame.Actors.Player.PlayerController;

import javax.swing.*;
import java.awt.*;

public class Fist {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int power = 1;
    public static int speed = 2;
    public static boolean visible;
    public static String direction;
    public static Image left = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
    public static Image right = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
    public static Image up = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
    public static Image down = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();

    public static void start(){
        x = PlayerController.x + PlayerController.width / 3;
        y = PlayerController.y + PlayerController.height / 3;
        width = 192 / 2;
        height = 192 / 2;
        Fist.visible = true;
    }

    public static void tick() {
        if (direction != null) {
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
            }
        }
    }

    public static void powerUp() {
        power += 1;
    }
}
