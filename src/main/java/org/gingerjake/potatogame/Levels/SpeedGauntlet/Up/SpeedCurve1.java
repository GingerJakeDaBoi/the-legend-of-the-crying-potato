package org.gingerjake.potatogame.Levels.SpeedGauntlet.Up;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class SpeedCurve1 extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Curve1.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    String playerDirection;
    BadPotato enemy;
    boolean finished = false;
    boolean switching;
    int nextLvlX = GamePanel.width;
    int nextLvlY = 0;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the top part of the bottom left side
    int hitbox1aX = 780;
    int hitbox1aY = 621;
    int hitbox1aW = GamePanel.width;
    int hitbox1aH = 1;
    //now the left part
    int hitbox1bX = 773;
    int hitbox1bY = 621;
    int hitbox1bW = 1;
    int hitbox1bH = GamePanel.height;
    //Hitbox for the top side
    int hitbox2X = 0;
    int hitbox2Y = 0;
    int hitbox2W = GamePanel.width;
    int hitbox2H = 225;
    //hitbox for the right side
    int hitbox3X = 0;
    int hitbox3Y = 0;
    int hitbox3W = 276;
    int hitbox3H = 861;



    public SpeedCurve1() {
        super(gsm);

    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();

        enemy = new BadPotato(1115, 300, 64, 64, 5,
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

        if (PlayerController.x + PlayerController.width > hitbox1aX && PlayerController.x < hitbox1aX + hitbox1aW && PlayerController.y + PlayerController.height > hitbox1aY && PlayerController.y < hitbox1aY + hitbox1aH) {
            PlayerController.y = 621 - PlayerController.height;
        }
        if (PlayerController.x + PlayerController.width > hitbox1bX && PlayerController.x < hitbox1bX + hitbox1bW && PlayerController.y + PlayerController.height > hitbox1bY && PlayerController.y < hitbox1bY + hitbox1bH) {
            PlayerController.x = 773 - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox2X && PlayerController.x < hitbox2X + hitbox2W && PlayerController.y + PlayerController.height > hitbox2Y && PlayerController.y < hitbox2Y + hitbox2H) {
            PlayerController.y = 225;
        }
        if (PlayerController.x + PlayerController.width > hitbox3X && PlayerController.x < hitbox3X + hitbox3W && PlayerController.y + PlayerController.height > hitbox3Y && PlayerController.y < hitbox3Y + hitbox3H) {
            PlayerController.x = hitbox3W;
        }

        if(finished) {
            if(PlayerController.x == GamePanel.width - PlayerController.width) {
                switching = true;
            }
            if(switching) {
                if(nextLvlX > 0) {
                    currentLvlX -= 6;
                    nextLvlX -= 6;
                } else {
                    GameStateManager.setState(new SpeedHorizontal1());
                }
            }
        }
    }
}
