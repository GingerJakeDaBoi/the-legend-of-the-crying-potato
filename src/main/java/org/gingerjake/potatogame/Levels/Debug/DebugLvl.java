package org.gingerjake.potatogame.Levels.Debug;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class DebugLvl extends GameState {
    String playerDirection;
    BadPotato debugEnemy1, debugEnemy2;

    int debugEnemy1Health, debugEnemy1x, debugEnemy1y;
    int debugEnemy2Health, debugEnemy2x, debugEnemy2y;
    boolean debugEnemy1Dead, debugEnemy2Dead;

    public DebugLvl() {
        super(gsm);
    }

    @Override
    public void init() {
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(GamePanel.width / 2, GamePanel.height / 2, 108, 192, 2,"Fist");
        }
        PlayerController.enable();
        GamePanel.gameStarted = true;
        playerDirection = "right";

        if(debugEnemy1Health != 0) {
            debugEnemy1.setHealth(debugEnemy1Health);
        }
        if(debugEnemy2Health != 0) {
            debugEnemy2.setHealth(debugEnemy2Health);
        }
        if(debugEnemy1x != 0) {
            debugEnemy1.setX(debugEnemy1x);
        }
        if(debugEnemy2x != 0) {
            debugEnemy2.setX(debugEnemy2x);
        }
        if(debugEnemy1y != 0) {
            debugEnemy1.setY(debugEnemy1y);
        }
        if(debugEnemy2y != 0) {
            debugEnemy2.setY(debugEnemy2y);
        }
        if(debugEnemy1Dead) {
            debugEnemy1.setHealth(0);
        }
        if(debugEnemy2Dead) {
            debugEnemy2.setHealth(0);
        }

        debugEnemy1 = new BadPotato(100, 100, 64, 64,5,
                1,1,new ImageIcon("Assets/Dummy/Red.png").getImage());
        debugEnemy2 = new BadPotato(300, 300, 64, 64,5,
                1,1,new ImageIcon("Assets/Dummy/Green.png").getImage());
    }

    public void draw(Graphics g) {
        if(playerDirection.equals("right")) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if(playerDirection.equals("left")) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if(debugEnemy1.isEnabled()) {
            g.drawImage(debugEnemy1.getAsset(), debugEnemy1.getX(), debugEnemy1.getY(),
                    debugEnemy1.getWidth(), debugEnemy1.getHeight(), null);
        }

        if(debugEnemy2.isEnabled()) {
            g.drawImage(debugEnemy2.getAsset(), debugEnemy2.getX(), debugEnemy2.getY(),
                    debugEnemy2.getWidth(), debugEnemy2.getHeight(), null);
        }
        fistUI(g);
        heartUI(g);

        //GameState.debugInfo(g);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Is Enemy1 Dead: " + debugEnemy1.isDead(), 10, 50);
        g.drawString("Enemy1 Health: " + debugEnemy1.getHealth(), 10, 70);
        g.drawString("Enemy1 Speed: " + debugEnemy1.getSpeed(), 10, 90);
        g.drawString("Enemy1 width: " + debugEnemy1.getWidth(), 10, 110);
        g.drawString("Enemy1 height: " + debugEnemy1.getHeight(), 10, 130);
        g.drawString("Enemy1 x: " + debugEnemy1.getX(), 10, 150);
        g.drawString("Enemy1 y: " + debugEnemy1.getY(), 10, 170);
        g.drawString("Is Enemy1 enabled: " + debugEnemy1.isEnabled(), 10, 190);

    }

    @Override
    public void tick() {
        if(PlayerController.righting) {
            playerDirection = "right";
        }
        if(PlayerController.lefting) {
            playerDirection = "left";
        }

        PlayerController.tick();

        if(debugEnemy1.isEnabled() || debugEnemy2.isEnabled()) {
            if(debugEnemy1.isDead()) {
                debugEnemy1.setEnabled(false);
            } else {
                debugEnemy1.tick();
            }
            if (debugEnemy2.isDead()) {
                debugEnemy2.setEnabled(false);
            } else {
                debugEnemy2.tick();
            }
        }

        debugEnemy1Health = debugEnemy1.getHealth();
        debugEnemy1x = debugEnemy1.getX();
        debugEnemy1y = debugEnemy1.getY();
        debugEnemy1Dead = debugEnemy1.isDead();
        debugEnemy2Health = debugEnemy2.getHealth();
        debugEnemy2x = debugEnemy2.getX();
        debugEnemy2y = debugEnemy2.getY();
        debugEnemy2Dead = debugEnemy2.isDead();
    }
}