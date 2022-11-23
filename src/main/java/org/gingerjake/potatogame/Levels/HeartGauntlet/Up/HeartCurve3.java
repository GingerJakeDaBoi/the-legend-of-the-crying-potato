package org.gingerjake.potatogame.Levels.HeartGauntlet.Up;

import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class HeartCurve3 extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/Curve1.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/HeartGauntlet/Horizontal.png").getImage();
    String playerDirection;
    final boolean finished = true;
    boolean switching;
    int nextLvlX = GamePanel.width;
    final int nextLvlY = 0;
    int currentLvlX = 0;
    final int currentLvlY = 0;
    //Hitbox for the top part of the bottom left side
    final Hitbox hitbox1a = new Hitbox(780, 621, GamePanel.width, 1,"up");
    //now the left part
    final Hitbox hitbox1b = new Hitbox(773, 621, 1, GamePanel.height, "left");
    //Hitbox for the top side
    final Hitbox hitbox2 = new Hitbox(275, 220, GamePanel.width, 1, "down");
    //hitbox for the right side
    final Hitbox hitbox3 = new Hitbox(0, 0, 276, 861, "right");

    public HeartCurve3() {
        super(gsm);
    }

    @Override
    public void init() {
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;

        playerDirection = "left";
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl,nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right") && !switching) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left") && !switching) {
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

        hitbox1a.tick();
        hitbox1b.tick();
        hitbox2.tick();
        hitbox3.tick();

        if(finished) {
            if(PlayerController.x == GamePanel.width - PlayerController.width) {
                switching = true;
            }
            if(switching) {
                if(nextLvlX > 0) {
                    currentLvlX -= 6;
                    nextLvlX -= 6;
                } else {
                    GameStateManager.setState(new HeartHorizontal2());
                }
            }
        }
    }
}
