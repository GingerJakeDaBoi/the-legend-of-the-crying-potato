package org.gingerjake.potatogame.Actors.Player;

import org.gingerjake.potatogame.AudioEngine;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PlayerController {
    public static final Image playerRight = new ImageIcon("Assets/Potato/NewMainR.png").getImage();
    public static final Image playerLeft = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int health = 3;
    public static int speed;
    public static int velocityX;
    public static int velocityY;
    public static boolean heartGiven;
    public static boolean speedGiven;
    public static boolean hurting;
    public static boolean enabled;
    public static boolean uping;
    public static boolean downing;
    public static boolean lefting;
    public static boolean righting;
    private static String weapon;

    public static void hurt() {
        if (!hurting) {
            health -= 1;
            hurting = true;
            AudioEngine.playSound(new File("Assets/Sounds/Player/hurt.wav"));

            //if the player was hurt, they can't be hurt again for 1 second
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    hurting = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

    public static void spawn(int x, int y, int width, int height, int speed, String weapon) {
        PlayerController.x = x;
        PlayerController.y = y;
        PlayerController.width = width;
        PlayerController.height = height;
        PlayerController.speed = speed;
        PlayerController.weapon = weapon;
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static void tick() {
        String currentLevel = GameStateManager.getState();
        if (uping) {
            if (velocityY > -speed) {
                velocityY--;
            }
            if (downing) {
                velocityY = 0;
            }
        } else if (velocityY < 0) {
            velocityY++;
        }

        if (downing) {
            if (velocityY < speed) {
                velocityY++;
            }
            if (uping) {
                velocityY = 0;
            }
        } else if (velocityY > 0) {
            velocityY--;
        }

        if (lefting) {
            if (velocityX > -speed) {
                velocityX--;
            }
            if (righting) {
                velocityX = 0;
            }
        } else if (velocityX < 0) {
            velocityX++;
        }

        if (righting) {
            if (velocityX < speed) {
                velocityX++;
            }
            if (lefting) {
                velocityX = 0;
            }
        } else if (velocityX > 0) {
            velocityX--;
        }

        x += velocityX/10;
        y += velocityY/10;


        if (uping) { //GAINING SPEED DOES NOT AFFECT THESE VALUES
            y -= 1;
        }
        if (downing) {
            y += 1;
        }
        if (lefting) {
            x -= 1;
        }
        if (righting) {
            x += 1;
        }

        if (!currentLevel.equals("TestSpace")) {
            if (PlayerController.x > GamePanel.width - PlayerController.width) {
                PlayerController.x = GamePanel.width - PlayerController.width;
            }
            if (PlayerController.x < 0) {
                PlayerController.x = 0;
            }
            if (PlayerController.y > GamePanel.height - PlayerController.height) {
                PlayerController.y = GamePanel.height - PlayerController.height;
            }
            if (PlayerController.y < 0) {
                PlayerController.y = 0;
            }
        }
        if (weapon.equals("Fist")) {
            Fist.tick();
        }
    }

    public static void giveHeart() {
        if (!heartGiven) {
            health += 1;
            heartGiven = true;
        }
    }

    public static void giveSpeed() {
        if (!speedGiven) {
            speed += 40;
            speedGiven = true;
        }
    }

    public static void giveDamage() {
        if (weapon.equals("fist")) {
            Fist.powerUp();
        }
    }
}