package org.gingerjake.potatogame.Levels.HeartGauntlet;

import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class HeartCurve1 extends GameState {
    final Image background = new ImageIcon("Assets/HeartGauntlet/Curve2.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/HeartGauntlet/PathFork.png").getImage();
    String playerDirection;
    boolean finished = true;
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;    //Hitbox for the bottom part of the top right side
    Hitbox hitbox1a = new Hitbox(790,225,GamePanel.width,1,"down");
    //now the left part
    Hitbox hitbox1b = new Hitbox(773,0,1,205,"left");
    Hitbox hitbox2 = new Hitbox(0,622,GamePanel.width,1,"up");
    Hitbox hitbox3 = new Hitbox(0,0,276,861,"right");

    public HeartCurve1() {
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
        hitbox1a.tick();
        hitbox1b.tick();
        hitbox2.tick();
        hitbox3.tick();

        if(finished) {
            if(PlayerController.y == 0) {
                switching = true;
            }
            if(switching) {
                if(nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    GameStateManager.setState(new HeartFork());
                }
            }
        }

    }
}
