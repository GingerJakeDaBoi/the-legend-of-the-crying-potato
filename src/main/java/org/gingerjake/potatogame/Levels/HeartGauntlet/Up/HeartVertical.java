package org.gingerjake.potatogame.Levels.HeartGauntlet.Up;

import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class HeartVertical extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/Vertical.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/HeartGauntlet/Curve1.png").getImage();
    String playerDirection;
    boolean finished = true;
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    Hitbox hitbox1 = new Hitbox(773,0,811,861,"left");
    //hitbox for the left side
    Hitbox hitbox2 = new Hitbox(0,0,276,861,"right");
    public HeartVertical() {
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

        hitbox1.tick();
        hitbox2.tick();

        if(finished) {
            if(PlayerController.y == 0) {
                switching = true;
            }
            if(switching) {
                if(nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    GameStateManager.setState(new HeartCurve3());
                }
            }
        }

    }
}
