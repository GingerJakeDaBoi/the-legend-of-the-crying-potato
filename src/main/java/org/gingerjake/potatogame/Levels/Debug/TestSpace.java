package org.gingerjake.potatogame.Levels.Debug;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.HeartGauntlet.HeartEntrance;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEnd;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEntrance;

import javax.swing.*;
import java.awt.*;

public class TestSpace extends GameState {
    final Image background = new ImageIcon("Assets/Dummy/GreenDitherBG.png").getImage();
    String playerDirection;

    public TestSpace() {
        super(gsm);
    }

    @Override
    public void init() {
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(500, 500, 108, 192, 80, "Fist");
        }
        playerDirection = "right";
        PlayerController.enable();
        GamePanel.gameStarted = true;

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

        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if (PlayerController.x < 0) {
            GameStateManager.setState(new HeartEntrance());
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.y < 0) {
            if (!SpeedEnd.completed) {
                GameStateManager.setState(new SpeedEntrance());
            } else {
                PlayerController.y = 0;
            }
        }
    }
}
