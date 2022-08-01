package org.gingerjake.potatogame;

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
            if(e.getID() == KeyEvent.KEY_PRESSED) {
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    Fist.left();
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    Fist.right();
                }
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    Fist.up();
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    Fist.down();
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Potato.health -= 1;
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    GameStateManager.setState(new TestScreen());
                }
            }
            return false;
        });
    }
}