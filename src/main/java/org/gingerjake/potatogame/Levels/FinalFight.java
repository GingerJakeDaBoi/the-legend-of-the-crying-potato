package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Final.FinalAmmo;
import org.gingerjake.potatogame.Actors.Enemies.Final.FinalBoss;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FinalFight extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image background = new ImageIcon("Assets/Dummy/GreenDitherBG.png").getImage();
    Image boss = new ImageIcon("Assets/Dummy/Red.png").getImage();
    Image bossAmmo = new ImageIcon("Assets/Dummy/Green.png").getImage();
    Image fist;


    public FinalFight() {
        super(gsm);
        FinalBoss.health = 3;
        FinalBoss.spawn(900, 300,96,96);
        FinalBoss.enable();
        new Thread(FinalBoss::chase).start();
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
//        g.drawImage(background, 0, 0, GamePanel.width, GamePanel.height, null);

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if (FinalBoss.enabled) {
            g.drawImage(boss, FinalBoss.x, FinalBoss.y, FinalBoss.width, FinalBoss.height, null);
        }
        if(FinalAmmo.enabled){
            g.drawImage(bossAmmo, FinalAmmo.x, FinalAmmo.y, FinalAmmo.width, FinalAmmo.height, null);
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

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        g.drawString("Boss Health: " + FinalBoss.health, 0, 80);
        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 120);
        g.drawString("Boss Location: " + FinalBoss.x + ", " + FinalBoss.y, 0, 160);
        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 200);
        g.drawString("Fist direction: " + Fist.direction, 0, 240);
        g.drawString("FinalAmmo enabled: " + FinalAmmo.enabled, 0, 280);
        g.drawString("FinalAmmo Location: " + FinalAmmo.x + ", " + FinalAmmo.y, 0, 320);
        g.drawString("FinalAmmo randomX: " + FinalAmmo.randomX + " FinalAmmo randomY: " + FinalAmmo.randomY, 0, 360);

    }

    @Override
    public void tick() {

    }
}
