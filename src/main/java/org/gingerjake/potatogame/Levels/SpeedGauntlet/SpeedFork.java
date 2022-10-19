package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedHorizontal2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;

import javax.swing.*;
import java.awt.*;

public class SpeedFork extends GameState {
    Image background = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    Image nextUpLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    Image nextRightLvl = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    String playerDirection;
    BadPotato enemy1,enemy2;
    boolean finished = false;
    boolean switching;
    boolean switchUp;
    boolean switchRight;
    int nextUpLvlX = 0;
    int nextUpLvlY = -GamePanel.height;
    int nextRightLvlX = GamePanel.width;
    int nextRightLvlY = 0;
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
    //Hitbox for the bottom part of the top left side
    int hitbox2aX = 790;
    int hitbox2aY = 225;
    int hitbox2aW = GamePanel.width;
    int hitbox2aH = 1;
    //now the left part
    int hitbox2bX = 773;
    int hitbox2bY = 0;
    int hitbox2bW = 1;
    int hitbox2bH = 205;
    //hitbox for the right side
    int hitbox3X = 0;
    int hitbox3Y = 0;
    int hitbox3W = 276;
    int hitbox3H = 861;


    public SpeedFork() {
        super(gsm);
    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();

        enemy1 = new BadPotato(450, 50, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
        enemy2 = new BadPotato(1350, 300, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextUpLvl, nextUpLvlX, nextUpLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextRightLvl, nextRightLvlX, nextRightLvlY, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right") && !switching) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left") && !switching) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if(enemy1.isEnabled()) {
            g.drawImage(enemy1.getAsset(), enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight(), null);
        }
        if(enemy2.isEnabled()) {
            g.drawImage(enemy2.getAsset(), enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight(), null);
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
        if(enemy1.isEnabled()) {
            enemy1.tick();
        }
        if(enemy2.isEnabled()) {
            enemy2.tick();
        }

        if (enemy1.isDead() && enemy2.isDead()) {
            finished = true;
        }

        if (PlayerController.x + PlayerController.width > hitbox1aX && PlayerController.x < hitbox1aX + hitbox1aW && PlayerController.y + PlayerController.height > hitbox1aY && PlayerController.y < hitbox1aY + hitbox1aH) {
            PlayerController.y = 621 - PlayerController.height;
        }
        if (PlayerController.x + PlayerController.width > hitbox1bX && PlayerController.x < hitbox1bX + hitbox1bW && PlayerController.y + PlayerController.height > hitbox1bY && PlayerController.y < hitbox1bY + hitbox1bH) {
            PlayerController.x = 773 - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox2aX && PlayerController.x < hitbox2aX + hitbox2aW && PlayerController.y + PlayerController.height > hitbox2aY && PlayerController.y < hitbox2aY + hitbox2aH) {
            PlayerController.y = 225;
        }
        if (PlayerController.x + PlayerController.width > hitbox2bX && PlayerController.x < hitbox2bX + hitbox2bW && PlayerController.y + PlayerController.height > hitbox2bY && PlayerController.y < hitbox2bY + hitbox2bH) {
            PlayerController.x = 773 - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox3X && PlayerController.x < hitbox3X + hitbox3W && PlayerController.y + PlayerController.height > hitbox3Y && PlayerController.y < hitbox3Y + hitbox3H) {
            PlayerController.x = hitbox3W;
        }
        if (finished) {
            if (PlayerController.y == 0) {
                switching = true;
                switchUp = true;
                switchRight = false;
            }
            if (PlayerController.x == GamePanel.width - PlayerController.width) {
                switching = true;
                switchUp = false;
                switchRight = true;
            }
            if (switching) {
                if (switchUp) {
                    PlayerController.y -= 5;

                    if (nextUpLvlY < 0) {
                        currentLvlY += 3;
                        nextUpLvlY += 3;
                    } else {
                        GameStateManager.setState(new SpeedVertical1());
                    }
                }
                if (switchRight) {
                    PlayerController.x += 5;

                    if (nextRightLvlX > 0) {
                        currentLvlX -= 6;
                        nextRightLvlX -= 6;
                    } else {
                        GameStateManager.setState(new SpeedHorizontal2());
                    }
                }
            }
        }
    }
}
