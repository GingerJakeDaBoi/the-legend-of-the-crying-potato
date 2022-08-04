package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.Levels.PauseMenu;
import org.gingerjake.potatogame.Levels.PotatoFarmChase;
import org.gingerjake.potatogame.Levels.WorldHub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!Player.uping) {
                        Player.uping = true;
                        new Thread(Player::moveUp).start();
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection > 0) {
                            PauseMenu.Selection -= 1;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!Player.downing) {
                        Player.downing = true;
                        new Thread(Player::moveDown).start();
                    }
                    if (PauseMenu.paused) {
                        if (PauseMenu.Selection < 2) {
                            PauseMenu.Selection += 1;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!Player.lefting) {
                        Player.lefting = true;
                        new Thread(Player::moveLeft).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!Player.righting) {
                        Player.righting = true;
                        new Thread(Player::moveRight).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!gameStarted.get()) {
                        gameStarted.set(true);
                        GameStateManager.setState(new WorldHub());
                    }
                    if (PauseMenu.Selection == 0) {
                        GameStateManager.setState(new WorldHub());
                    } else if (PauseMenu.Selection == 1) {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                    } else if (PauseMenu.Selection == 2) {
                        try {
                            SaveSystem.saveGame();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (WorldHub.FarmSelected) {
                        GameStateManager.setState(new PotatoFarmChase());
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (!PauseMenu.paused) {
                        PauseMenu.paused = true;
                        GameStateManager.setState(new PauseMenu());
                    } else {
                        PauseMenu.paused = false;
                        GameStateManager.setState(new WorldHub());
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
                    if(!Fist.visible) {
                        new Thread(Fist::right).start();
                    }
                }

            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    Player.uping = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Player.downing = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Player.lefting = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Player.righting = false;
                }
            }
            return false;
        });
    }
}

