package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.DummyEnemy;
import org.gingerjake.potatogame.Actors.Fist;
import org.gingerjake.potatogame.Actors.Potato;
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
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (!gameStarted.get()) {
                            gameStarted.set(true);
                            GameStateManager.setState(new TestScreen());
                        }
                        break;
                    case KeyEvent.VK_UP: {
                        Potato.Y -= 10;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        Potato.Y += 10;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        Potato.X -= 10;
                        break;
                    }
                    case KeyEvent.VK_RIGHT: {
                        Potato.X += 10;
                        break;
                    }
                    case KeyEvent.VK_W: {
                        Fist.up();
                        break;
                    }
                    case KeyEvent.VK_S: {
                        Fist.down();
                        break;
                    }
                    case KeyEvent.VK_A: {
                        Fist.left();
                        break;
                    }
                    case KeyEvent.VK_D: {
                        Fist.right();
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        DummyEnemy.spawn(100,100);
                        break;
                    }
                }
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_A:
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_S:
                        break;
                }
            }
            return false;
        });
    }
}