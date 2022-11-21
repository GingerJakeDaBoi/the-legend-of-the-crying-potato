package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;

public class Controls {
    public static int controlMode;
    public static void moveUp() {
        PlayerController.uping = true;
    }

    public static void moveDown() {
        PlayerController.downing = true;
    }

    public static void moveLeft() {
        PlayerController.lefting = true;
    }

    public static void moveRight() {
        PlayerController.righting = true;
    }

    public static void fistUp() {
        if (!Fist.visible) {
            Fist.direction = "up";
            Fist.start();
        }
    }

    public static void fistDown() {
        if (!Fist.visible) {
            Fist.direction = "down";
            Fist.start();
        }
    }

    public static void fistLeft() {
        if (!Fist.visible) {
            Fist.direction = "left";
            Fist.start();
        }
    }

    public static void fistRight() {
        if (!Fist.visible) {
            Fist.direction = "right";
            Fist.start();
        }
    }

    public static void cancelFist() {
        Fist.visible = false;
    }
}



