package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Debug.DebugLvl;
import org.gingerjake.potatogame.Levels.Menus.ControlMenu;
import org.gingerjake.potatogame.Levels.Menus.EndScreen;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.Debug.TestSpace;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controls {
    public static int controlMode;
    public static void menuUp() {
        if (PauseMenu.paused) {
            if(ControlMenu.enabled) {
                if (ControlMenu.Selection > 0) {
                    ControlMenu.Selection--;
                }
            } else if (PauseMenu.Selection > 0) {
                PauseMenu.Selection--;
            }
        }
    }

    public static void menuDown() {
        if (PauseMenu.paused) {
            if(ControlMenu.enabled) {
                if (ControlMenu.Selection < 1) {
                    ControlMenu.Selection++;
                }
            } else if (PauseMenu.Selection < 2) {
                PauseMenu.Selection++;
            }
        }
    }

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

    public static void startGame() throws FileNotFoundException {
        if (!GamePanel.gameStarted) {
            SaveSystem.loadGame();
            if(GameState.debug) {
                GameStateManager.setState(new DebugLvl());
            } else {
                GameStateManager.setState(new TestSpace());
            }
        }
    }

    public static void endGame() {
        if (EndScreen.gameEnded) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }

    public static void select() throws IOException {
        if (PauseMenu.paused) {
            if(ControlMenu.enabled) {
                if(ControlMenu.Selection == 0) {
                    controlMode = 0;
                    SaveSystem.saveGame();
                }
                if(ControlMenu.Selection == 1) {
                    controlMode = 1;
                    SaveSystem.saveGame();
                }
            } else if (PauseMenu.Selection == 0) {
                GameStateManager.resume();
            } else if (PauseMenu.Selection == 1) {
                GameStateManager.setState(new ControlMenu());
            } else if (PauseMenu.Selection == 2) {
                System.out.println("Thanks for playing!");
                System.exit(0);
            } else {
                System.out.println("Error: Invalid selection");
                System.exit(1);
            }
        }
    }
}



