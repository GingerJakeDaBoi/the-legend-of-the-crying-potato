package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.TestSpace;
import org.gingerjake.potatogame.Levels.Menus.EndScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (PlayerController.enabled) {
                        if (!PlayerController.uping) {
                            PlayerController.uping = true;
                            new Thread(PlayerController::moveUp).start();
                        }
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection > 0) {
                            PauseMenu.Selection--;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (PlayerController.enabled) {
                        if (!PlayerController.downing) {
                            PlayerController.downing = true;
                            new Thread(PlayerController::moveDown).start();
                        }
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection < 1) {
                            PauseMenu.Selection++;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!PlayerController.lefting) {
                        PlayerController.lefting = true;
                        new Thread(PlayerController::moveLeft).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!PlayerController.righting) {
                        PlayerController.righting = true;
                        new Thread(PlayerController::moveRight).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (!Fist.visible) {
                        new Thread(Fist::up).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (!Fist.visible) {
                        new Thread(Fist::down).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    if (!Fist.visible) {
                        new Thread(Fist::left).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (!Fist.visible) {
                        new Thread(Fist::right).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (!PauseMenu.paused) {
                        GameStateManager.pause();
                    } else {
                        GameStateManager.resume();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!GamePanel.gameStarted) {
                        GameStateManager.setState(new TestSpace());
                    }
                    if (EndScreen.gameEnded) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection == 0) {
                            GameStateManager.resume();
                        }
                        if (PauseMenu.Selection == 1) {
                            System.out.println("Thanks for playing!");
                            System.exit(0);
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Fist.visible = false;
                }

            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
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
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                Fist.visible = false;
            }
            return false;
        });
    }
}


