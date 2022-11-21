package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private boolean isRunning = false; // is the game running?
    public static boolean gameStarted = false; // If the first level has been started
    public static int width = 1600; //Window Width
    public static int height = 900; //Window Height
    public final static int FPS = 60;
    public static final long TARGET_TIME = 1000 / FPS; //Target Time
    private GameStateManager gsm = new GameStateManager();

    public GamePanel() {
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        start();
    }

    private void start() {
        isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void tick() {
        gsm.tick(); }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0,0,width,height);
        gsm.draw(g);
    }
    @SuppressWarnings("BusyWait")
    public void run() {
        long start,elapsed,wait;
        gsm = new GameStateManager();

        while(isRunning) {
            start = System.nanoTime();
            tick();
            repaint();
            invalidate();

            elapsed = System.nanoTime()-start;
            wait = (TARGET_TIME -elapsed) / 1000000;

            if(wait <=0) wait = 5;
            try {
                Thread.sleep(wait);
                GameStateManager.currentLevel = GameStateManager.getState();
                if(PlayerController.health <= 0) {
                    if(PlayerController.heartGiven) {
                        PlayerController.health = 4;
                    } else {
                        PlayerController.health = 3;
                    }
                }

                //update width and height of the screen
                width = getWidth();
                height = getHeight();

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}