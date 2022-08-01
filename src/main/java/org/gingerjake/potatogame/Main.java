package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Dummy.Enemy;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Potato;
import org.gingerjake.potatogame.Screens.TestScreen;

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

        KeyListener gameInput = new KeyListener();
        gameWindow.addKeyListener(gameInput);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: {
                        Potato.moveUp();
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        Potato.moveDown();
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        Potato.moveLeft();
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        Potato.moveRight();
                        break;
                    }

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
                            GameStateManager.setState(new TestScreen());
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_SPACE:
                        Enemy.health += 1;
                        break;
                    case KeyEvent.VK_E:
                        new Thread(Enemy::chase).start();
                        break;
                }
            }
            return false;
        });
    }
}