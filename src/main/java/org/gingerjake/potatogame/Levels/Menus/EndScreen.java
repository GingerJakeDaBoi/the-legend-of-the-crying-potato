package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class EndScreen extends GameState {
    public static boolean gameEnded;

    public EndScreen() {
        super(gsm);


    }

    @Override
    public void init() {
        gameEnded = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        if (PlayerController.heartGiven && PlayerController.speedGiven && PlayerController.powerGiven) {
            g.drawString("You Win!", GamePanel.width / 2 - 100, GamePanel.height / 2);
        } else {
            g.drawString("YOU LOST", 0, 40);
        }

    }

    @Override
    public void tick() {

    }
}
