package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class EndScreen extends GameState {
    public EndScreen() {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("You Win!",GamePanel.width/2,GamePanel.height/2);
    }

    @Override
    public void tick() {

    }
}
