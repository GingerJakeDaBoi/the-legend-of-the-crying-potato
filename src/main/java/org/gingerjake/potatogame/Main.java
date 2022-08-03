package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.Levels.PauseMenu;
import org.gingerjake.potatogame.Levels.PotatoFarmChase;
import org.gingerjake.potatogame.Levels.WorldHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
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
                switch (e.getKeyCode()) {
                    //if the user presses two arrow keys at the same time, both will be executed
                    case KeyEvent.VK_UP:
                        if (PauseMenu.paused) {
                            if (PauseMenu.Selection > 0) {
                                PauseMenu.Selection --;
                                break;
                            }
                            break;

                        }
                        new Thread(Player::moveUp).start();
                        break;
                    case KeyEvent.VK_DOWN:
                        if (PauseMenu.paused) {
                            if (PauseMenu.Selection < 2) {
                                PauseMenu.Selection ++;
                                break;
                            }

                        }
                        if (!PauseMenu.paused) {
                            new Thread(Player::moveDown).start();
                            break;
                        }
                    case KeyEvent.VK_LEFT:
                        new Thread(Player::moveLeft).start();
                        break;
                    case KeyEvent.VK_RIGHT:
                        new Thread(Player::moveRight).start();
                        break;

                    case KeyEvent.VK_A:
                        if (!Fist.FistRunning) {
                            new Thread(Fist::left).start();
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (!Fist.FistRunning) {
                            new Thread(Fist::right).start();
                        }
                        break;
                    case KeyEvent.VK_W:
                        if (!Fist.FistRunning) {
                            new Thread(Fist::up).start();
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (!Fist.FistRunning) {
                            new Thread(Fist::down).start();
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        if (!gameStarted.get()) {
                            gameStarted.set(true);
                            GameStateManager.setState(new WorldHub());
                            Fist.enemyList.add("Enemy");
                            try {
                                SaveSystem.loadGame();
                            } catch (FileNotFoundException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (WorldHub.FarmSelected) {
                            GameStateManager.setState(new PotatoFarmChase());
                        }
                        if(PauseMenu.paused) {
                            if(PauseMenu.Selection == 0) {
                                PauseMenu.paused = false;
                                GameStateManager.setState(new WorldHub());
                            } else if(PauseMenu.Selection == 1) {
                                try {
                                    SaveSystem.saveGame();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                System.exit(0);
                            } else if(PauseMenu.Selection == 2) {
                                try {
                                    SaveSystem.saveGame();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        if (!PauseMenu.paused) {
                            GameStateManager.setState(new PauseMenu());
                        } else {
                            GameStateManager.setState(new WorldHub());
                            PauseMenu.paused = false;
                        }
                        break;

                }
            }
            return false;
        });
    }
}