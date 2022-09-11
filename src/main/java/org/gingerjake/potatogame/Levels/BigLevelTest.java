package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BigLevelTest extends GameState {
    int biglevelx = 0;
    int biglevely = 0;
    int biglevelwidth = 7164;
    int biglevelheight = 4254;
    Image biglevelimage = new ImageIcon("Assets/WorldMap/WorldMap.png").getImage();
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image fist;

    public BigLevelTest() {
        super(gsm);
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(500, 500, 108, 192);

        }
        PlayerController.enable();
        GamePanel.gameStarted = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(biglevelimage, biglevelx, biglevely, biglevelwidth, biglevelheight, null);

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);
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

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 80);
        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 120);
        g.drawString("Fist direction: " + Fist.direction, 0, 160);

        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
            biglevelx = biglevelx - 4;
        }
        if (PlayerController.x < 0) {
            PlayerController.x = 0;
            biglevelx = biglevelx + 4;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
            biglevely = biglevely - 4;
        }
        if (PlayerController.y < 0) {
            PlayerController.y = 0;
            biglevely = biglevely + 4;
        }
    }

    @Override
    public void tick() {

    }
}
