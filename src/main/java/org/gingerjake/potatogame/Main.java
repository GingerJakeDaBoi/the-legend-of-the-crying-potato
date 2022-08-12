package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.TestLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends KeyListener {

    public static void main(String[] args) {
        AtomicBoolean gameStarted = new AtomicBoolean(false);
        JFrame gameWindow = new JFrame("Potato Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(GamePanel.width, GamePanel.height);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gameWindow.add(new GamePanel(), BorderLayout.CENTER);
        gameWindow.setVisible(true);
        gameWindow.setResizable(false);

        KeyListener gameInput = new KeyListener();
        gameWindow.addKeyListener(gameInput);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (gameStarted.get()) {
                        if (!PauseMenu.paused) {
                            PauseMenu.paused = true;
                            GameStateManager.setState(new PauseMenu());
                        } else {
                            PauseMenu.paused = false;
                            GameStateManager.setState(new TestLevel());
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!gameStarted.get()) {
                        gameStarted.set(true);
                        GameStateManager.setState(new TestLevel());
                    }
                    if (PauseMenu.Selection == 0) {
                        GameStateManager.setState(new TestLevel());
                    } else if (PauseMenu.Selection == 1) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    }


                }

            }
//            if (e.getID() == KeyEvent.KEY_RELEASED) {
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    if (Player.uping) {
//                        Player.uping = false;
//                    }
//                }
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    if (Player.downing) {
//                        Player.downing = false;
//                    }
//                }
//                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//                    if (Player.lefting) {
//                        Player.lefting = false;
//                    }
//                }
//                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//                    if (Player.righting) {
//                        Player.righting = false;
//                    }
//                }

            return false;
        });
    }
}


