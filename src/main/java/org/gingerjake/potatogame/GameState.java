package org.gingerjake.potatogame;


import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;

import java.awt.*;

public abstract class GameState {

    public abstract void init();

    public static GameStateManager gsm;

    public void draw(Graphics g) {

    }

    public static void debugInfo(Graphics g) {
        boolean debug = true;
        if(debug) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("Enemy Health: " + Enemy.health, 0, 80);
            g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 120);
            g.drawString("Enemy Location: " + Enemy.x + ", " + Enemy.y, 0, 160);
            g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 200);
            g.drawString("Fist direction: " + Fist.direction, 0, 240);
            g.drawString("Control Mode: " + Controls.controlMode, 0, 280);
            g.drawString("GamePanel width: " + GamePanel.width + "GamePanel Height: " + GamePanel.height, 0, 320);
        }
    }

    public abstract void tick();

    public GameState(GameStateManager gsm) {
        GameState.gsm = gsm;
        init();
    }

}