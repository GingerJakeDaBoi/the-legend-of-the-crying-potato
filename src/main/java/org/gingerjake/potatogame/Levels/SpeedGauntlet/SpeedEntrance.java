package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpeedEntrance extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();

    Image fist;
    boolean finished;
    boolean switching;
    int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    int hitbox1X = 773;
    int hitbox1Y = 0;
    int hitbox1W = 811;
    int hitbox1H = 861;
    //hitbox for the left side
    int hitbox2X = 0;
    int hitbox2Y = 0;
    int hitbox2W = 276;
    int hitbox2H = 861;



    public SpeedEntrance() {
        super(gsm);
    }

    @Override
    public void init() {

        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl,nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

//        g.drawImage(debugBox,PlayerController.x,PlayerController.y,PlayerController.width,PlayerController.height,null);
        g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

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

        GameState.debugInfo(g);

    }

    @Override
    public void tick() {

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

        if (PlayerController.x + PlayerController.width > hitbox1X && PlayerController.x < hitbox1X + hitbox1W && PlayerController.y + PlayerController.height > hitbox1Y && PlayerController.y < hitbox1Y + hitbox1H) {
            PlayerController.x = hitbox1X - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox2X && PlayerController.x < hitbox2X + hitbox2W && PlayerController.y + PlayerController.height > hitbox2Y && PlayerController.y < hitbox2Y + hitbox2H) {
            PlayerController.x = hitbox2W;
        }

        if(finished) {
            if(PlayerController.y == 0) {
                switching = true;
            }
            if(switching) {
                PlayerController.playerRight = new ImageIcon((String) null).getImage();
                PlayerController.switching = true;
                if(nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    PlayerController.playerRight = new ImageIcon("Assets/Potato/NewMainL.png").getImage();
                    PlayerController.switching = false;
                    GameStateManager.setState(new SpeedFork());
                }
            }
        }

    }
}
