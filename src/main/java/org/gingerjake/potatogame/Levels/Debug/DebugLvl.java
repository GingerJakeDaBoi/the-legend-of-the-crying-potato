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

    public DebugLvl() {
        super(gsm);
    }

    @Override
    public void init() {
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(GamePanel.width / 2, GamePanel.height / 2, 108, 192, 2, "Fist");
        }
        PlayerController.enable();
        GamePanel.gameStarted = true;
        playerDirection = "right";

        debugEnemy1 = new BadPotato(100, 100, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
        debugEnemy2 = new BadPotato(300, 300, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Green.png").getImage());
    }

    public void draw(Graphics g) {
        if (playerDirection.equals("right")) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if (debugEnemy1.isEnabled()) {
            g.drawImage(debugEnemy1.getAsset(), debugEnemy1.getX(), debugEnemy1.getY(),
                    debugEnemy1.getWidth(), debugEnemy1.getHeight(), null);
        }

        if (debugEnemy2.isEnabled()) {
            g.drawImage(debugEnemy2.getAsset(), debugEnemy2.getX(), debugEnemy2.getY(),
                    debugEnemy2.getWidth(), debugEnemy2.getHeight(), null);
        }
        fistUI(g);
        heartUI(g);

        GameState.debugInfo(g);

    }

    @Override
    public void tick() {
        if (PlayerController.righting) {
            playerDirection = "right";
        }
        if (PlayerController.lefting) {
            playerDirection = "left";
        }

        PlayerController.tick();

        if (debugEnemy1.isEnabled() || debugEnemy2.isEnabled()) {
            if (debugEnemy1.isDead()) {
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
    }
}