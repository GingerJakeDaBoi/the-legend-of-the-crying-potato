package org.gingerjake.potatogame.Levels.SpeedGauntlet.Right;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

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
    final int hitbox1aX = 815;
    final int hitbox1aY = 0;
    final int hitbox1aW = 1;
    final int hitbox1aH = 230;
    //now the bottom
    final int hitbox1bX = 0;
    final int hitbox1bY = 240;
    final int hitbox1bW = 800;
    final int hitbox1bH = 1;
    //hitbox for the right side
    final int hitbox2X = 1310;
    final int hitbox2Y = 0;
    final int hitbox2W = GamePanel.height;
    final int hitbox2H = GamePanel.width;
    //hitbox for the bottom side
    final int hitbox3X = 0;
    final int hitbox3Y = 622;
    final int hitbox3W = GamePanel.width;
    final int hitbox3H = GamePanel.height;


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

        if (PlayerController.x + PlayerController.width > hitbox3X && PlayerController.x < hitbox3X + hitbox3W && PlayerController.y + PlayerController.height > hitbox3Y && PlayerController.y < hitbox3Y + hitbox3H) {
            PlayerController.y = 622 - PlayerController.height;
        }
        if (PlayerController.x + PlayerController.width > hitbox2X && PlayerController.x < hitbox2X + hitbox2W && PlayerController.y + PlayerController.height > hitbox2Y && PlayerController.y < hitbox2Y + hitbox2H) {
            PlayerController.x = hitbox2X - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox1aX && PlayerController.x < hitbox1aX + hitbox1aW && PlayerController.y + PlayerController.height > hitbox1aY && PlayerController.y < hitbox1aY + hitbox1aH) {
            PlayerController.x = hitbox1aX;
        }
        if (PlayerController.x + PlayerController.width > hitbox1bX && PlayerController.x < hitbox1bX + hitbox1bW && PlayerController.y + PlayerController.height > hitbox1bY && PlayerController.y < hitbox1bY + hitbox1bH) {
            PlayerController.y = hitbox1bY;
        }

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
