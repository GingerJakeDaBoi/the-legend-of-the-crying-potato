package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Chaser;
import org.gingerjake.potatogame.Actors.Enemies.SlowChaser;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Actors.Upgrades.ExtraHeart;
import org.gingerjake.potatogame.Actors.Upgrades.SpeedBoots;
import org.gingerjake.potatogame.Actors.Upgrades.StrengthChalice;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HubSpace extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/Red.png").getImage();
    Image background = new ImageIcon("Assets/Dummy/GreenDitherBG.png").getImage();
    Image boots = new ImageIcon("Assets/Dummy/potion.png").getImage();
    Image chalice = new ImageIcon("Assets/Dummy/rock.jpg").getImage();

    Image fist;

    public HubSpace() {
        super(gsm);
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(500, 500, 192, 192);
            Chaser.spawn(400, 400, 64, 64);SlowChaser.spawn(400,500,64,64);
            ExtraHeart.spawn(300,300,64,64);
            SpeedBoots.spawn(300,200,64,64);
            StrengthChalice.spawn(300,400,64,64);

        }
        PlayerController.enable();
        GamePanel.gameStarted = true;

    }

    @Override
    public void init() {


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(background, 0, 0, GamePanel.width, GamePanel.height, null);

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if(StrengthChalice.enabled) {
            g.drawImage(chalice, StrengthChalice.x, StrengthChalice.y, StrengthChalice.width, StrengthChalice.height, null);
        }
        if(SpeedBoots.enabled) {
            g.drawImage(boots, SpeedBoots.x, SpeedBoots.y, SpeedBoots.width, SpeedBoots.height, null);
        }
        if(ExtraHeart.enabled) {
            g.drawImage(playerHeart, ExtraHeart.x, ExtraHeart.y, ExtraHeart.width, ExtraHeart.height, null);
        }
        if (Chaser.enabled) {
            g.drawImage(chaser, Chaser.x, Chaser.y, Chaser.width, Chaser.height, null);
        }
        if (SlowChaser.enabled) {
            g.drawImage(chaser, SlowChaser.x, SlowChaser.y, SlowChaser.width, SlowChaser.height, null);
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

//        g.setFont(new Font("Arial", Font.BOLD, 20));
//        g.setColor(Color.WHITE);
//        g.drawString("Chaser Health: " + Chaser.health, 0, 80);
//        g.drawString("Slow Chaser Health: " + SlowChaser.health, 0, 120);
//        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 160);
//        g.drawString("Chaser Location: " + Chaser.x + ", " + Chaser.y, 0, 200);
//        g.drawString("Slow Chaser Location: " + SlowChaser.x + ", " + SlowChaser.y, 0, 240);
//        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 280);
//        g.drawString("Fist direction: " + Fist.direction, 0, 320);
//
//        g.drawString("Z: Give Heart, X: Give Speed, C: Give Power",0, 360);
//        g.drawString("Heart Given: " + PlayerController.heartGiven, 0, 400);
//        g.drawString("Speed Given: " + PlayerController.speedGiven, 0, 440);
//        g.drawString("Power Given: " + PlayerController.powerGiven, 0, 480);

        if(PlayerController.health == 4) {
            g.drawImage(playerHeart, 3, 0,48,48, null);
            g.drawImage(playerHeart, 54, 0,48,48, null);
            g.drawImage(playerHeart, 105, 0,48,48, null);
            g.drawImage(playerHeart, 156, 0,48,48, null);
        }
        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            if(PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if(PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if(PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }

        if(PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if(PlayerController.x < 0) {
            PlayerController.x = 0;
        }
        if(PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if(PlayerController.y < 0) {
            PlayerController.y = 0;
        }


    }

    @Override
    public void tick() {

    }
}
