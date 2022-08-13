package org.gingerjake.potatogame.Actors.Player.Attacks;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;

public class Fist {
    public static int x;
    public static int y;
    public static int width;
    public static int height;
    public static int speed = 5;
    public static boolean visible;
    public static String direction;

    @SuppressWarnings("BusyWait")
    public static void left() {
        visible = true;
        direction = "left";
        x = PlayerController.x;
        y = PlayerController.y;
        width = PlayerController.width / 2;
        height = PlayerController.height / 2;
        System.out.println("Fist left");
        while (visible) {
            x -= 1;
            if (x < 0) {
                visible = false;
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @SuppressWarnings("BusyWait")
    public static void right() {
        visible = true;
        direction = "right";
        x = PlayerController.x + PlayerController.width;
        y = PlayerController.y;
        width = PlayerController.width / 2;
        height = PlayerController.height / 2;
        System.out.println("Fist right");
        while (visible) {
            x += 1;
            if (x > GamePanel.width) {
                visible = false;
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @SuppressWarnings("BusyWait")
    public static void up() {
        visible = true;
        direction = "up";
        x = PlayerController.x;
        y = PlayerController.y;
        width = PlayerController.width / 2;
        height = PlayerController.height / 2;
        System.out.println("Fist up");
        while (visible) {
            y -= 1;
            if (y < 0) {
                visible = false;
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @SuppressWarnings("BusyWait")
    public static void down() {
        visible = true;
        direction = "down";
        x = PlayerController.x;
        y = PlayerController.y + PlayerController.height;
        width = PlayerController.width / 2;
        height = PlayerController.height / 2;
        System.out.println("Fist down");
        while (visible) {
            y += 1;
            if (y > GamePanel.height) {
                visible = false;
            }

            try {
                Thread.sleep(GamePanel.TARGET_TIME / speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
