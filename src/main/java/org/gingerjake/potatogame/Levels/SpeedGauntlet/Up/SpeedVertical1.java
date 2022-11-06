package org.gingerjake.potatogame.Levels.SpeedGauntlet.Up;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Actors.Hitbox;

import javax.swing.*;
import java.awt.*;

public class SpeedVertical1 extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/Curve1.png").getImage();
    String playerDirection;
    BadPotato enemy;
    boolean finished = false;
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    Hitbox hitbox1 = new Hitbox(773,0,811,861,"left");
    //hitbox for the left side
    Hitbox hitbox2 = new Hitbox(0,0,276,861,"right");



    public SpeedVertical1() {
        super(gsm);

    }

    @Override
    public void init() {
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();
        playerDirection = "right";

        enemy = new BadPotato(450, 50, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
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

        if(enemy.isEnabled()) {
            g.drawImage(enemy.getAsset(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), null);
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
        if(enemy.isEnabled()) {
            enemy.tick();
        }

        if(enemy.isDead()) {
            finished = true;
        }

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
                    GameStateManager.setState(new SpeedCurve1());
                }
            }
        }

    }
}
