package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Enemies.GenericEnemy;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.Levels.Menus.EndScreen;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private boolean isRunning = false;
    public static int width = 1600; //Window Width
    public static int height = 900; //Window Height
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
            int FPS = 60;
            long TARGET_TIME = 1000 / FPS;
            wait = (TARGET_TIME -elapsed) / 1000000;

            if(wait <=0) wait = 5;
            try {
                Thread.sleep(wait);
                if(Player.health <= 0) {
                    GameStateManager.setState(new EndScreen());
                    Player.health = 3;
                    GenericEnemy.spawned = false;
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