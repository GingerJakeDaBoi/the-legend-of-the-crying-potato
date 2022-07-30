package org.gingerjake.potatogame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public static boolean enterPressed;
    private boolean isRunning = false;
    public static final int width = 800; //Window Width
    public static final int height = 600; //Window Height
    private GameStateManager ssm = new GameStateManager();

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

    public void tick() {ssm.tick(); }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0,0,width,height);

        ssm.draw(g);
    }
    @SuppressWarnings("BusyWait")
    public void run() {
        long start,elapsed,wait;
        ssm = new GameStateManager();



        while(isRunning) {
            start = System.nanoTime();
            tick();
            repaint();

            elapsed = System.nanoTime()-start;
            int FPS = 60;
            long TARGET_TIME = 1000 / FPS;
            wait = (TARGET_TIME -elapsed) / 1000000;

            if(wait <=0) wait = 5;
            try {
                Thread.sleep(wait);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}