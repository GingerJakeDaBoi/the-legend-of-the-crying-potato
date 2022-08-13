package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class StartScreen extends GameState {

    public StartScreen() {
        super(gsm);
    }

    public void init() {

    }


    public void draw(Graphics g) {
        g.setColor(new Color(196, 103, 227, 255));
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Press Enter to start", 0, GamePanel.height - 5);

    }

    public void tick() {

    }
}

