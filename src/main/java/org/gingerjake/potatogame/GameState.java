package org.gingerjake.potatogame;


import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;

import javax.swing.*;
import java.awt.*;

public abstract class GameState {
    public static boolean debug = true;
    public abstract void init();

    public static GameStateManager gsm;

    public void draw(Graphics g) {

    }

    public static void debugInfo(Graphics g) {
        if(debug) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setColor(Color.WHITE);
            g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 120);
            g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 200);
            g.drawString("Fist direction: " + Fist.direction, 0, 240);
            g.drawString("Control Mode: " + Controls.controlMode, 0, 280);
            g.drawString("GamePanel width: " + GamePanel.width + "GamePanel Height: " + GamePanel.height, 0, 320);
        }
    }

    public static void heartUI(Graphics g) {
        Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
        Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
        if (PlayerController.health == 4) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            g.drawImage(playerHeart, 156, 0, 48, 48, null);
        }
        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
    }
    public abstract void tick();

    public GameState(GameStateManager gsm) {
        GameState.gsm = gsm;
        init();
    }

}