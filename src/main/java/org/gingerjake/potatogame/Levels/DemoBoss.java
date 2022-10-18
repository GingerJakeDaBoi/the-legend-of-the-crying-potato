package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Boss;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Controls;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class DemoBoss extends GameState {
    String playerDirection;
    Boss finalBoss;

    public DemoBoss() {
        super(gsm);
    }


    @Override
    public void init() {
        playerDirection = "right";
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(GamePanel.width / 2, GamePanel.height / 2, 108, 192, 10, "Fist");
            GamePanel.gameStarted = true;
        }
        PlayerController.enable();
        PlayerController.x = 250;
        PlayerController.y = 250;
        finalBoss = new Boss(1420, 300, 120, 90, 15,
                1, 1, new ImageIcon("Assets/Dummy/EvilPotatoBoss.png").getImage(), new ImageIcon("Assets/Dummy/Red.png").getImage());
        finalBoss.setPhase(0);

    }

    public void draw(Graphics g) {
        if (playerDirection.equals("right")) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if (finalBoss.isEnabled()) {
            g.drawImage(new ImageIcon("Assets/Dummy/Red.png").getImage(), finalBoss.getX(), finalBoss.getY(),
                    finalBoss.getWidth(), finalBoss.getHeight(), null);
            g.drawImage(finalBoss.getAsset(), finalBoss.getX(), finalBoss.getY(),
                    finalBoss.getWidth(), finalBoss.getHeight(), null);
        }
        if (finalBoss.isEnabled()) {
            if (finalBoss.isShooting()) {
                g.drawImage(finalBoss.getAmmoAsset(), finalBoss.getAmmoX(), finalBoss.getAmmoY(), 32, 32, null);
            }
        }

        fistUI(g);
        heartUI(g);

        //debugInfo(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Boss Health: " + finalBoss.getHealth(), 0, 80);
        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 120);
        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 160);
        g.drawString("Fist direction: " + Fist.direction, 0, 200);
        g.drawString("Control Mode: " + Controls.controlMode, 0, 240);
        g.drawString("GamePanel width: " + GamePanel.width + "GamePanel Height: " + GamePanel.height, 0, 280);
        g.drawString("Boss threshold: " + finalBoss.getThresholds(), 0, 320);
        g.drawString("Boss phase: " + finalBoss.getPhase(), 0, 360);
        if (finalBoss.getPhase() == 1) {
            g.drawString("Ammo direction: " + finalBoss.getAmmoDir(), 0, 400);
        }

    }

    @Override
    public void tick() {
        PlayerController.tick();
        if (finalBoss.isEnabled()) {
            finalBoss.tick();
        }

        if (PlayerController.righting) {
            playerDirection = "right";
        }
        if (PlayerController.lefting) {
            playerDirection = "left";
        }
    }
}
