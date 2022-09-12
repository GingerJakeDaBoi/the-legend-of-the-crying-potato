package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BigLevelTest extends GameState {
    public static int bgx;
    public static int bgy;
    int bgwidth = 7164;
    int bgheight = 4254;
    Image background = new ImageIcon("Assets/WorldMap/WorldMap.png").getImage();
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
        Enemy.spawn(2000,2000, 64,64);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, bgx, bgy, bgwidth, bgheight, null);

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if(Enemy.enabled){
            g.drawImage(Enemy.enemyAsset, Enemy.x, Enemy.y, Enemy.width, Enemy.height, null);
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

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 80);
        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 120);
        g.drawString("Fist direction: " + Fist.direction, 0, 160);
        g.drawString("Map Location: " + bgx + ", " + bgy, 0, 200);
        g.drawString("Enemy Location: " + Enemy.x + ", " + Enemy.y, 0, 240);

        //The rest of the If statements are for collision in this level.
        if (PlayerController.x > 1366) {
            PlayerController.x = 1366;
            bgx = bgx - 2;
            Enemy.x = Enemy.x - 2;
        }
        if (PlayerController.x < 200) {
            PlayerController.x = 200;
            bgx = bgx + 2;
            Enemy.x = Enemy.x + 2;
        }
        if (PlayerController.y > 560) {
            PlayerController.y = 560;
            bgy = bgy - 2;
            Enemy.y = Enemy.y - 2;
        }
        if (PlayerController.y < 100) {
            PlayerController.y = 100;
            Enemy.y = Enemy.y + 2;
            bgy = bgy + 2;
        }
        if(bgx > 0){
            bgx = 0;
        }
        if(bgx < -5578){
            bgx = -5578;
        }
        if(bgy > 0){
            bgy = 0;
        }
        if(bgy < -3392){
            bgy = -3392;

        }
    }

    @Override
    public void tick() {

    }
}
