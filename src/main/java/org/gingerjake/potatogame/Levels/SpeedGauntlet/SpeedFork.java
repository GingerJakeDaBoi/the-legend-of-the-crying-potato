package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedHorizontal2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpeedFork extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/Red.png").getImage();
    Image background = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    Image nextUpLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    Image nextRightLvl = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    Image fist;
    boolean finished;
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
        Enemy.disable();

        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();
        Enemy.spawn(450, 50, 64, 64, 2, 3);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextUpLvl, nextUpLvlX, nextUpLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextRightLvl, nextRightLvlX, nextRightLvlY, GamePanel.width, GamePanel.height, null);

//        g.drawImage(debugBox,PlayerController.x,PlayerController.y,PlayerController.width,PlayerController.height,null);
        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if (Enemy.enabled) {
            g.drawImage(chaser, Enemy.x, Enemy.y, Enemy.width, Enemy.height, null);
        }

        if (Fist.visible) {
            if (Objects.equals(Fist.direction, "left")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
            }
            if (Objects.equals(Fist.direction, "right")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
            }
            if (Objects.equals(Fist.direction, "up")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
            }
            if (Objects.equals(Fist.direction, "down")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();
            }
            g.drawImage(fist, Fist.x, Fist.y, Fist.width, Fist.height, null);
        }

        if (PlayerController.health == 4) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            g.drawImage(playerHeart, 156, 0, 48, 48, null);
        }
        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }

        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if (PlayerController.x < 0) {
            PlayerController.x = 0;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.y < 0) {
            PlayerController.y = 0;
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

        if (!Enemy.enabled) {
            finished = true;
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
                PlayerController.playerAsset = new ImageIcon((String) null).getImage();
                if (switchUp) {
                    PlayerController.y -= 5;

                    if (nextUpLvlY < 0) {
                        currentLvlY += 3;
                        nextUpLvlY += 3;
                    } else {
                        PlayerController.playerAsset = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
                        GameStateManager.setState(new SpeedVertical1());
                    }
                }
                if (switchRight) {
                    PlayerController.x += 5;

                    if (nextRightLvlX > 0) {
                        currentLvlX -= 3;
                        nextRightLvlX -= 3;
                    } else {
                        PlayerController.playerAsset = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
                        GameStateManager.setState(new SpeedHorizontal2());
                    }
                }
            }
        }

        GameState.debugInfo(g);

    }

    @Override
    public void tick() {

    }
}
