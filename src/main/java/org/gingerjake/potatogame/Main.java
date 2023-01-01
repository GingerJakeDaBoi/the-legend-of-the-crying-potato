package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.BossPrototypeFinal;
import org.gingerjake.potatogame.Levels.GameMenu;

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
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    PlayerController.giveSpeed();
                    PlayerController.giveDamage();
                    PlayerController.giveHeart();
                    PlayerController.health = 4;
                    GameStateManager.setState(new BossPrototypeFinal());
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!GameMenu.isPaused) {
                        PlayerController.uping = true;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (!GameMenu.isPaused) {
                        PlayerController.downing = true;
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!GameMenu.isPaused) {
                        PlayerController.lefting = true;
                    } else {
                        GameMenu.menuLeft();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (!GameMenu.isPaused) {
                        PlayerController.righting = true;
                    } else {
                        GameMenu.menuRight();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_W) {
                    Fist.up();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    Fist.down();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    Fist.left();
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    Fist.right();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Fist.cancel();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if(GameMenu.isPaused) {
                        GameStateManager.resume();
                    } else {
                        GameStateManager.pause();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER && GameMenu.isPaused) {
                    GameMenu.select();
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
            return false;
        });
    }
}


