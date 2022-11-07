package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.*;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GameState {
    public static boolean paused;
    public static boolean gameOver;
    public static int verticalSelection;
    public static int horizontalSelection;

    public PauseMenu() {
        super(gsm);
    }

    public static void select() {
        if (paused) {
            switch (Controls.currentMenu) {
                case "Pause":
                case "GameOver":
                    switch (horizontalSelection) {
                        case 0 -> GameStateManager.resume();
                        case 1 -> Controls.currentMenu = "Settings";
                        case 2 -> System.exit(0);
                    }
                    break;
                case "Settings":
                    if(horizontalSelection == 1) {
                        switch (Settings.activeSetting) {
                            case "Controls":
                                switch (verticalSelection) {
                                    case 0 -> Controls.controlMode = 0;
                                    case 1 -> Controls.controlMode = 1;
                                }
                                break;
                            case "Keybindings":
                                switch (verticalSelection) {
                                    case 0 -> Settings.moveUp = "W";
                                    case 1 -> Settings.moveDown = "S";
                                    case 2 -> Settings.moveLeft = "A";
                                    case 3 -> Settings.moveRight = "D";
                                    case 4 -> Settings.attackUp = "Up";
                                    case 5 -> Settings.attackDown = "Down";
                                    case 6 -> Settings.attackLeft = "Left";
                                    case 7 -> Settings.attackRight = "Right";
                                    case 8 -> Settings.pause = "Escape";
                                }
                        }
                    }
            }
        }
    }

    @Override
    public void init() {
        Controls.currentMenu = "Pause";
        paused = true;
        verticalSelection = -1;
        horizontalSelection = 0;
    }

    @Override
    public void draw(Graphics g) {
        if (PauseMenu.gameOver) {
            Controls.currentMenu = "GameOver";
        }

        switch (Controls.currentMenu) {
            case ("GameOver") -> {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, GamePanel.width, GamePanel.height);

                g.setColor(new Color(86, 34, 232, 255));
                g.fillRect(0, 0, GamePanel.width, 60);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Game Over", 61, 48);
            }
            case ("Pause") -> {
                g.drawImage(new ImageIcon("Assets/Dummy/PotatoFarmTestBG.jpg").getImage(), 0, 0, GamePanel.width, GamePanel.height, null);

                g.setColor(new Color(86, 34, 232, 255));
                g.fillRect(0, 0, GamePanel.width, 60);

                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.setColor(Color.WHITE);
                g.drawString(GameStateManager.version,61,48);
            }
            case ("Settings") -> {
                g.setColor(new Color(98, 98, 98, 255));
                g.fillRect(0, 60, GamePanel.width, GamePanel.height - 120);

                g.setColor(new Color(86, 34, 232, 255));
                g.fillRect(0, 0, GamePanel.width, 60);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Horizontal: " + horizontalSelection + " Vertical: " + verticalSelection + " Selected: " + Settings.activeSetting, 61, 48);

                for (int i = 60; i < GamePanel.height - 60; i += 50) {
                    g.setColor(new Color(128, 128, 128, 255));
                    g.fillRect(0,i,GamePanel.width,2);
                }

                g.setColor(new Color(128, 128, 128, 255));
                g.fillRect(GamePanel.width / 2 - 40,61,40,GamePanel.height - 122);

                if(horizontalSelection == 0) {
                    if (verticalSelection == 0) {
                        g.fillRect(0, 60, GamePanel.width / 2 - 40, 50);
                        Settings.activeSetting = "Controls";
                    }
                    if (verticalSelection == 1) {
                        g.fillRect(0, 110, GamePanel.width / 2 - 40, 50);
                        Settings.activeSetting = "Audio";
                    }
                    if (verticalSelection == 2) {
                        g.fillRect(0, 160, GamePanel.width / 2 - 40, 50);
                        Settings.activeSetting = "Keybindings";
                    }
                } else {
                    if (verticalSelection == 0) {
                        g.fillRect(GamePanel.width / 2, 110, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 1) {
                        g.fillRect(GamePanel.width / 2, 160, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 2) {
                        g.fillRect(GamePanel.width / 2, 210, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 3) {
                        g.fillRect(GamePanel.width / 2, 260, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 4) {
                        g.fillRect(GamePanel.width / 2, 310, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 5) {
                        g.fillRect(GamePanel.width / 2, 360, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 6) {
                        g.fillRect(GamePanel.width / 2, 410, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 7) {
                        g.fillRect(GamePanel.width / 2, 460, GamePanel.width / 2 - 40, 50);
                    }
                    if (verticalSelection == 8) {
                        g.fillRect(GamePanel.width / 2, 510, GamePanel.width / 2 - 40, 50);
                    }
                }
                for (int i = 2; i > -1; i--) {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 30));
                    g.drawString(Settings.settings[i], 5, 100 + (50 * i));
                }
                //if Settings.activeSetting is not null, draw the settings
                if (Settings.activeSetting != null) {
                    g.setColor(Color.WHITE);

                    switch (Settings.activeSetting) {
                        case "Controls" -> {
                            g.setFont(new Font("Arial", Font.BOLD, 30));
                            g.drawString("Control Mode", GamePanel.width / 2 + 5, 100);
                            g.setFont(new Font("Arial", Font.PLAIN, 20));
                            g.drawString("Keyboard: " + Settings.isKeyboardControls, GamePanel.width / 2 + 5, 150);
                            g.drawString("Controller: " + Settings.isControllerControls, GamePanel.width / 2 + 5, 200);

                        }
                        case "Audio" -> {
                            g.setFont(new Font("Arial", Font.BOLD, 30));
                            g.drawString("Audio", GamePanel.width / 2 + 5, 100);
                            g.setFont(new Font("Arial", Font.PLAIN, 20));
                            g.drawString("Music Volume: " + Settings.musicVolume, GamePanel.width / 2 + 5, 150);
                            g.drawString("Sound Volume: " + Settings.soundVolume, GamePanel.width / 2 + 5, 200);
                        }
                        case "Keybindings" -> {
                            g.setFont(new Font("Arial", Font.BOLD, 30));
                            g.drawString("Keybindings", GamePanel.width / 2 + 5, 100);
                            g.setFont(new Font("Arial", Font.PLAIN, 20));
                            g.drawString("Move Up: " + Settings.moveUp, GamePanel.width / 2 + 5, 150);
                            g.drawString("Move Down: " + Settings.moveDown, GamePanel.width / 2 + 5, 200);
                            g.drawString("Move Left: " + Settings.moveLeft, GamePanel.width / 2 + 5, 250);
                            g.drawString("Move Right: " + Settings.moveRight, GamePanel.width / 2 + 5, 300);
                            g.drawString("Attack Up: " + Settings.attackUp, GamePanel.width / 2 + 5, 350);
                            g.drawString("Attack Down: " + Settings.attackDown, GamePanel.width / 2 + 5, 400);
                            g.drawString("Attack Left: " + Settings.attackLeft, GamePanel.width / 2 + 5, 450);
                            g.drawString("Attack Right: " + Settings.attackRight, GamePanel.width / 2 + 5, 500);
                            g.drawString("Pause: " + Settings.pause, GamePanel.width / 2 + 5, 550);

                        }
                    }

                }
            }
        }

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);

        g.setColor(new Color(90, 43, 115, 255));
        g.fillRect(0, GamePanel.height - 60, GamePanel.width, 60);

        g.drawImage(new ImageIcon("Assets/GUI/Heart.png").getImage(), 8, 3, 50, 50, null);



        //if Selection is 0, draw a checkmark next to resume. if Selection is 1, draw a checkmark next to exit.
        g.setColor(new Color(115, 55, 145, 255));
        if(verticalSelection == -1) {
            if (horizontalSelection == 0) {
                g.fillRect(0, GamePanel.height - 60, 200, 60);
            }
            if (horizontalSelection == 1) {
                g.fillRect(215, GamePanel.height - 60, 215, 60);
            }
            if (horizontalSelection == 2) {
                g.fillRect(445, GamePanel.height - 60, 107, 60);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Resume", 0, GamePanel.height - 12);
        g.drawString("Settings", 220, GamePanel.height -12);
        g.drawString("Exit", 450, GamePanel.height - 12);
    }

    @Override
    public void tick() {

    }
}
