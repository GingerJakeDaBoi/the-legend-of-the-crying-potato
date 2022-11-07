package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Controls {
    public static String currentMenu;
    public static int controlMode;

    public static void menuLeft() {
        if (Objects.equals(Controls.currentMenu, "Pause") || Objects.equals(Controls.currentMenu, "GameOver")) {
            if(PauseMenu.horizontalSelection > 0) {
                PauseMenu.horizontalSelection--;
            }
        } else {
            if(PauseMenu.horizontalSelection > 0) {
                PauseMenu.horizontalSelection--;
                if(PauseMenu.verticalSelection > 2) {
                    PauseMenu.verticalSelection = 2;
                }
            }
        }
    }

    public static void menuRight() {
        if(Objects.equals(Controls.currentMenu, "Pause") || Objects.equals(Controls.currentMenu, "GameOver")) {
            if(PauseMenu.horizontalSelection < 2) {
                PauseMenu.horizontalSelection++;
            }
        }
        if(Objects.equals(Controls.currentMenu, "Settings")) {
            if(PauseMenu.horizontalSelection < 1 && PauseMenu.verticalSelection != -1) {
                PauseMenu.horizontalSelection++;
            }
            if(PauseMenu.verticalSelection == -1) {
                if(PauseMenu.horizontalSelection < 2) {
                    PauseMenu.horizontalSelection++;
                }
            }
        }
    }

    public static void menuUp() {
        if (PauseMenu.paused) {
            if(!(Objects.equals(Controls.currentMenu, "Pause") || Objects.equals(Controls.currentMenu, "GameOver"))) {
                if (PauseMenu.verticalSelection > 0) {
                    PauseMenu.verticalSelection--;
                }
                if (PauseMenu.verticalSelection == -1) {
                    PauseMenu.verticalSelection = 2;
                    PauseMenu.horizontalSelection = 0;
                }
            }
        }
    }

    public static void menuDown() {
        if (PauseMenu.paused) {
            if(!Objects.equals(Controls.currentMenu, "Pause") || !Objects.equals(Controls.currentMenu, "GameOver")) {
                if(PauseMenu.horizontalSelection == 0) {
                    if (PauseMenu.verticalSelection != -1) {
                        if (PauseMenu.verticalSelection < Settings.settings.length) {
                            PauseMenu.verticalSelection++;
                        }
                        if (PauseMenu.verticalSelection == Settings.settings.length - 1) {
                            PauseMenu.verticalSelection = -1;
                        }
                    }
                } else if(PauseMenu.horizontalSelection == 1) {
                    if (PauseMenu.verticalSelection != -1) {
                        switch (Settings.activeSetting) {
                            case ("Controls") -> {
                                if (PauseMenu.verticalSelection < Settings.controlModes.length) {
                                    PauseMenu.verticalSelection++;
                                }
                                if (PauseMenu.verticalSelection == Settings.controlModes.length) {
                                    PauseMenu.verticalSelection = -1;
                                    PauseMenu.horizontalSelection = 0;
                                }
                            }
                            case ("Audio") -> {
                                if (PauseMenu.verticalSelection < Settings.audioModes.length) {
                                    PauseMenu.verticalSelection++;
                                }
                                if (PauseMenu.verticalSelection == Settings.audioModes.length) {
                                    PauseMenu.verticalSelection = -1;
                                    PauseMenu.horizontalSelection = 0;
                                }
                            }
                            case ("Keybindings") -> {
                                if (PauseMenu.verticalSelection < Settings.keybinds.length) {
                                    PauseMenu.verticalSelection++;
                                }
                                if (PauseMenu.verticalSelection == Settings.keybinds.length) {
                                    PauseMenu.verticalSelection = -1;
                                    PauseMenu.horizontalSelection = 0;
                                }
                            }
                        }
                    }
                }
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
            if (!PauseMenu.paused) {
                PauseMenu.paused = true;
                GameStateManager.pause();
            }
        }
    }

    public static void resume() {
        if (PauseMenu.paused) {
            PauseMenu.paused = false;
            GameStateManager.resume();
        }
    }

    public static void startGame() throws FileNotFoundException {
        if (!GamePanel.gameStarted) {
            SaveSystem.loadGame();
        }
    }

    public static void select() throws IOException {
        PauseMenu.select();
    }
}



