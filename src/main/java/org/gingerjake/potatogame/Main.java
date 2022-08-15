package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.HubSpace;
import org.gingerjake.potatogame.Levels.Menus.EndScreen;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Main extends KeyListener {

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("The Legend of the Crying Potato");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(GamePanel.width, GamePanel.height);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gameWindow.add(new GamePanel(), BorderLayout.CENTER);
        gameWindow.setVisible(true);

        KeyListener gameInput = new KeyListener();
        gameWindow.addKeyListener(gameInput);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if(e.getKeyCode() == KeyEvent.VK_P) {
                    new Thread(PlayerController::giveDamage).start();
                    new Thread(PlayerController::giveHeart).start();
                    new Thread(PlayerController::giveSpeed).start();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!PlayerController.uping) {
                        PlayerController.uping = true;
                        new Thread(PlayerController::moveUp).start();
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection > 0) {
                            PauseMenu.Selection -= 1;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!PlayerController.downing) {
                        PlayerController.downing = true;
                        new Thread(PlayerController::moveDown).start();
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection < 1) {
                            PauseMenu.Selection += 1;
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
                    if (GamePanel.gameStarted) {
                        if (!EndScreen.gameEnded) {
                            if (!PauseMenu.paused) {
                                PauseMenu.paused = true;
                                PauseMenu.previousState = GameStateManager.getState();
                                GameStateManager.setState(new PauseMenu());
                            } else {
                                PauseMenu.paused = false;
                                GameStateManager.setState(new HubSpace());
                            }
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!GamePanel.gameStarted) {
                        GameStateManager.setState(new HubSpace());
                    }
                    if (PauseMenu.Selection == 0) {
                        if (Objects.equals(PauseMenu.previousState, "HubSpace")) {
                            GameStateManager.setState(new HubSpace());
                        }
                        PlayerController.enable();
                    } else if (PauseMenu.Selection == 1) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }
                    if (EndScreen.gameEnded) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }


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
            return false;
        });
    }
}


