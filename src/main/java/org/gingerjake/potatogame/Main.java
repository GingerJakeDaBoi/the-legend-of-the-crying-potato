package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;

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
                    Controls.moveUp();
                    Controls.menuUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    Controls.moveDown();
                    Controls.menuDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    Controls.moveLeft();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    Controls.moveRight();
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
                    Controls.startGame();
                    Controls.endGame();
                    if (PauseMenu.paused) {
                        Controls.select();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Controls.cancelFist();
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


