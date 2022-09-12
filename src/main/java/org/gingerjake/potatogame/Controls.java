package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.BigLevelTest;
import org.gingerjake.potatogame.Levels.Menus.EndScreen;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;

public class Controls {
    public static void menuUp() {
        if (PauseMenu.paused) {
            if (PauseMenu.Selection > 0) {
                PauseMenu.Selection--;
            }
        }
    }

    public static void menuDown() {
        if (PauseMenu.paused) {
            if (PauseMenu.Selection < 1) {
                PauseMenu.Selection++;
            }
        }
    }

    public static void moveUp() {
        if (PlayerController.enabled) {
            if (!PlayerController.uping) {
                PlayerController.uping = true;
                new Thread(PlayerController::moveUp).start();
            }
        }
    }

    public static void moveDown() {
        if (PlayerController.enabled) {
            if (!PlayerController.downing) {
                PlayerController.downing = true;
                new Thread(PlayerController::moveDown).start();
            }
        }
    }

    public static void moveLeft() {
        if (!PlayerController.lefting) {
            PlayerController.lefting = true;
            new Thread(PlayerController::moveLeft).start();
        }
    }

    public static void moveRight() {
        if (!PlayerController.righting) {
            PlayerController.righting = true;
            new Thread(PlayerController::moveRight).start();
        }
    }

    public static void fistUp() {
        if (!Fist.visible) {
            new Thread(Fist::up).start();
        }
    }

    public static void fistDown() {
        if (!Fist.visible) {
            new Thread(Fist::down).start();
        }
    }

    public static void fistLeft() {
        if (!Fist.visible) {
            new Thread(Fist::left).start();
        }
    }

    public static void fistRight() {
        if (!Fist.visible) {
            new Thread(Fist::right).start();
        }
    }

    public static void cancelFist() {
        Fist.visible = false;
    }

    public static void pause() {
        if (GamePanel.gameStarted) {
            if (!PauseMenu.paused) {
                PauseMenu.paused = true;
                if (EndScreen.gameEnded) {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
                GameStateManager.pause();
            }
        }
    }

    public static void resume() {
        if (PauseMenu.paused) {
            PauseMenu.paused = false;
            GameStateManager.resume();
            if (EndScreen.gameEnded) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
            GameStateManager.resume();
        }
    }

    public static void startGame() {
        if (!GamePanel.gameStarted) {
            GameStateManager.setState(new BigLevelTest());
        }
    }

    public static void endGame() {
        if (EndScreen.gameEnded) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }

    public static void select() {
        if (PauseMenu.paused) {
            if (PauseMenu.Selection == 0) {
                GameStateManager.resume();
            } else {
                System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }
}



