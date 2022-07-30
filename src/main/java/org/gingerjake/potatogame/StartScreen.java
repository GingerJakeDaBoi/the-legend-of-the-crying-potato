package org.gingerjake.potatogame;

import java.awt.*;

public class StartScreen extends GameState {
    public StartScreen(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {

    }

    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,0));
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Press Enter to start", 0, (int) (GamePanel.height * .945));

    }

    public void tick() {

    }
}

