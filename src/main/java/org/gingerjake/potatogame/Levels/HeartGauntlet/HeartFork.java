package org.gingerjake.potatogame.Levels.HeartGauntlet;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Left.HeartHorizontal1;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Up.HeartVertical;

import javax.swing.*;
import java.awt.*;

public class HeartFork extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/PathFork.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    String playerDirection;
    boolean finished = false;

    public HeartFork() {
        super(gsm);
    }

    @Override
    public void init() {
        PlayerController.x = 455;
        PlayerController.y = 585;

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

        if(PlayerController.x <= 0) {
            GameStateManager.setState(new HeartHorizontal1());
        }
        if(PlayerController.y <= 0) {
            GameStateManager.setState(new HeartVertical());
        }

    }
}
