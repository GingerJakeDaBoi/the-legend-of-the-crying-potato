package org.gingerjake.potatogame.Levels.SpeedGauntlet.Right;

import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpeedCurve2 extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/Red.png").getImage();
    Image background = new ImageIcon("Assets/SpeedGauntlet/Curve2.png").getImage();
    Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical2.png").getImage();
    Image fist;
    boolean finished;
    boolean switching;
    boolean switchUp;
    boolean switchRight;
    int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right part of the top right side
    int hitbox1aX = 815;
    int hitbox1aY = 0;
    int hitbox1aW = 1;
    int hitbox1aH = 230;
    //now the bottom
    int hitbox1bX = 0;
    int hitbox1bY = 240;
    int hitbox1bW = 800;
    int hitbox1bH = 1;
    //hitbox for the right side
    int hitbox2X = 1310;
    int hitbox2Y = 0;
    int hitbox2W = GamePanel.height;
    int hitbox2H = GamePanel.width;
    //hitbox for the bottom side
    int hitbox3X = 0;
    int hitbox3Y = 622;
    int hitbox3W = GamePanel.width;
    int hitbox3H = GamePanel.height;


    public SpeedCurve2() {
        super(gsm);
        Enemy.disable();

        PlayerController.x = 625;
        PlayerController.y = 405;
        PlayerController.enable();
        Enemy.spawn(1020, 115, 64, 64, 2, 3);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl, nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

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
                PlayerController.switching = true;
                if (switchUp) {
                    PlayerController.y -= 5;

                    if (nextLvlY < 0) {
                        currentLvlY += 3;
                        nextLvlY += 3;
                    } else {
                        PlayerController.playerAsset = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
                        PlayerController.switching = false;
                        GameStateManager.setState(new SpeedVertical2());
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
