package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.GameMenu;

public class Controls {
    public static int controlMode;
    public static void moveUp() {
        if (!GameMenu.isPaused) {
            PlayerController.uping = true;
        } else {
            GameMenu.menuUp();
        }
    }

    public static void moveDown() {
        if (!GameMenu.isPaused) {
            PlayerController.downing = true;
        } else {
            GameMenu.menuDown();
        }
    }

    public static void moveLeft() {
        if (!GameMenu.isPaused) {
            PlayerController.lefting = true;
        } else {
            GameMenu.menuLeft();
        }
    }

    public static void moveRight() {
        if (!GameMenu.isPaused) {
            PlayerController.righting = true;
        } else {
            GameMenu.menuRight();
        }
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

    public static void pause() {
        if (GamePanel.gameStarted) {
            if (!GameMenu.isPaused) {
                GameMenu.isPaused = true;
                GameStateManager.pause();
            }
        }
    }

    public static void resume() {
        if (GameMenu.isPaused) {
            GameMenu.isPaused = false;
            GameStateManager.resume();
        }
    }
}



