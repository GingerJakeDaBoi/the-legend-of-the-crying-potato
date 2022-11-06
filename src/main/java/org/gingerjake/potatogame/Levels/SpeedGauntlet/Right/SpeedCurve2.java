package org.gingerjake.potatogame.Levels.SpeedGauntlet.Right;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Actors.Hitbox;

import javax.swing.*;
import java.awt.*;

public class SpeedCurve2 extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Curve2.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical2.png").getImage();
    String playerDirection;
    BadPotato enemy;
    boolean finished = false;
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right part of the top right side
    Hitbox hitbox1a = new Hitbox(815, 0, 1, 230,"right");
    //now the bottom
    Hitbox hitbox1b = new Hitbox(0, 240, 800, 1,"down");
    //hitbox for the right side
    Hitbox hitbox2 = new Hitbox(1310,0,GamePanel.width,GamePanel.height,"left");
    //hitbox for the bottom side
    Hitbox hitbox3 = new Hitbox(0, 622, GamePanel.width, GamePanel.height, "up");


    public SpeedCurve2() {
        super(gsm);

    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 65;
        PlayerController.y = 335;
        PlayerController.enable();

        enemy = new BadPotato(1115, 300, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl, nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

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

        hitbox1a.tick();
        hitbox1b.tick();
        hitbox2.tick();
        hitbox3.tick();

        if (finished) {
            if (PlayerController.y == 0) {
                switching = true;
            }
            if (switching) {
                PlayerController.y -= 5;

                if (nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    GameStateManager.setState(new SpeedVertical2());
                }
            }
        }

    }
}
