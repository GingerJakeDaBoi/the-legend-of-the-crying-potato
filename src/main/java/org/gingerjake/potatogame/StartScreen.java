package org.gingerjake.potatogame;

import java.awt.*;

public class StartScreen extends GameState {
    public StartScreen(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setColor(Color.WHITE);
        g.drawString("Press Space to start", GamePanel.width / 2 - 50, GamePanel.height / 2);

    }

    public void tick() {

    }
}

