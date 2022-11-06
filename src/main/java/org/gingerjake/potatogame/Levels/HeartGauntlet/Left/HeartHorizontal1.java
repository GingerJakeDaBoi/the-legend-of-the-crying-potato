package org.gingerjake.potatogame.Levels.HeartGauntlet.Left;

import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class HeartHorizontal1 extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/Horizontal.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/HeartGauntlet/Curve1.png").getImage();
    String playerDirection;
    boolean finished = true; // TODO: Change this to false
    boolean switching;
    int nextLvlX = -GamePanel.width;
    final int nextLvlY = 0;
    int currentLvlX = 0;
    final int currentLvlY = 0;
    //Hitbox for the top part of the bottom left side
    Hitbox hitbox1 = new Hitbox(0, 220, GamePanel.width, 1,"down");
    //Hitbox for the bottom part of the top left side
    Hitbox hitbox2 = new Hitbox(0,622,GamePanel.width,1,"up");

    public HeartHorizontal1() {
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
            if(PlayerController.x <= 0) {
                switching = true;
            }
            if(switching) {
                if(nextLvlX < 0) {
                    currentLvlX += 6;
                    nextLvlX += 6;
                } else {
                    GameStateManager.setState(new HeartCurve2());
                }
            }
        }

    }
}
