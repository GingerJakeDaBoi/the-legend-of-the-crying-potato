package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class EndScreen extends GameState {
    public static boolean gameEnded;

    public EndScreen() {
        super(gsm);
        gameEnded = true;

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("YOU LOST", 0, 40);

    }

    @Override
    public void tick() {

    }
}
