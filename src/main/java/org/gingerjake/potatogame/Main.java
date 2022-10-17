package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.DemoBoss;
import org.gingerjake.potatogame.Levels.Menus.ControlMenu;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends KeyListener {

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("The Legend of the Crying Potato");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(GamePanel.width, GamePanel.height);
        gameWindow.setLocationRelativeTo(null);

        gameWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("Assets/GUI/Heart.png"));
        gameWindow.add(new GamePanel(), BorderLayout.CENTER);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);

        KeyListener gameInput = new KeyListener();

        gameWindow.addKeyListener(gameInput);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        manager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Controls.menuUp();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Controls.menuDown();
                }
                if (!PauseMenu.paused) {
                    if (Controls.controlMode == 0) {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            Controls.moveUp();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            Controls.moveDown();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            Controls.moveLeft();

                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            Controls.moveRight();
                        }
                    }
                    if (Controls.controlMode == 1) {
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            Controls.menuUp();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            Controls.menuDown();
                        }

                        if (e.getKeyCode() == KeyEvent.VK_I) {
                            Controls.moveUp();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_K) {
                            Controls.moveDown();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_J) {
                            Controls.moveLeft();
                        }
                        if (e.getKeyCode() == KeyEvent.VK_L) {
                            Controls.moveRight();
                        }
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_P) {
                    PlayerController.giveSpeed();
                    PlayerController.giveDamage();
                    PlayerController.giveHeart();
                    PlayerController.health = 4;
                    GameStateManager.setState(new DemoBoss());
                }

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    Controls.fistUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    Controls.fistDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    Controls.fistLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    Controls.fistRight();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (!PauseMenu.paused) {
                        Controls.pause();
                    } else {
                        Controls.resume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        Controls.startGame();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Controls.endGame();
                    if (PauseMenu.paused) {
                        try {
                            Controls.select();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Controls.cancelFist();
                }
            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (!ControlMenu.enabled) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (PlayerController.uping) {
                            PlayerController.uping = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (PlayerController.downing) {
                            PlayerController.downing = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (PlayerController.lefting) {
                            PlayerController.lefting = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (PlayerController.righting) {
                            PlayerController.righting = false;
                        }
                    }

                    if (e.getKeyCode() == KeyEvent.VK_I) {
                        if (PlayerController.uping) {
                            PlayerController.uping = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_K) {
                        if (PlayerController.downing) {
                            PlayerController.downing = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_J) {
                        if (PlayerController.lefting) {
                            PlayerController.lefting = false;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_L) {
                        if (PlayerController.righting) {
                            PlayerController.righting = false;
                        }
                    }

                }
            }
            return false;
        });
    }
}


