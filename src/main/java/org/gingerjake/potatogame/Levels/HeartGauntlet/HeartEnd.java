package org.gingerjake.potatogame.Levels.HeartGauntlet;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class HeartEnd extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/End.png").getImage();
    String playerDirection;

    public HeartEnd() {
        super(gsm);
    }

    @Override
    public void init() {
        PlayerController.x = 1390;
        PlayerController.y = 322;

        playerDirection = "left";

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right")) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left")) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        fistUI(g);
        heartUI(g);

        debugInfo(g);

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

    }
}
