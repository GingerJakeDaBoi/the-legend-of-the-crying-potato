package org.gingerjake.potatogame.Levels.SpeedGauntlet.Right;

import org.gingerjake.potatogame.Actors.Enemies.SlowChaser;
import org.gingerjake.potatogame.Actors.Enemies.SlowChaser2;
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

    Image fist;

    public SpeedCurve2() {
        super(gsm);

        PlayerController.x = 49;
        PlayerController.y = 348;
        PlayerController.enable();

        SlowChaser.health = 3;
        SlowChaser.spawn(490, 351, 96, 96);
        SlowChaser.enable();
        new Thread(SlowChaser::chase).start();

        SlowChaser2.health = 3;
        SlowChaser2.spawn(1000, 100, 96, 96);
        SlowChaser2.enable();
        new Thread(SlowChaser2::chase).start();


    }

    @Override
    public void init() {


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(background, 0, 0, GamePanel.width, GamePanel.height, null);

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if (SlowChaser.enabled) {
            g.drawImage(chaser, SlowChaser.x, SlowChaser.y, SlowChaser.width, SlowChaser.height, null);
        }
        if (SlowChaser2.enabled) {
            g.drawImage(chaser, SlowChaser2.x, SlowChaser2.y, SlowChaser2.width, SlowChaser2.height, null);
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

        //TODO: Fix wall collision
        if (!(PlayerController.x > 785)){
            if(PlayerController.y < 240) {
                System.out.println("Moving because the player is at x: " + PlayerController.x + " and y: " + PlayerController.y);
                PlayerController.y = 240;
            }
        } else {
            if(PlayerController.x < 790) {
                PlayerController.x = 790;
            }

        }

        //TODO: Change collision and background to VerticalFlipped
        if (PlayerController.y > 450) {
            PlayerController.y = 450;
        }
        if (PlayerController.x > 1133) {
            PlayerController.x = 1133;
        }
        if (PlayerController.x <= 0) {
            PlayerController.x = 0;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if (PlayerController.y < 0) {
            GameStateManager.setState(new SpeedVertical2());
        }

//        g.setFont(new Font("Arial", Font.BOLD, 20));
//        g.setColor(Color.WHITE);
//        g.drawString("Chaser 1 Health: " + SlowChaser.health, 0, 80);
//        g.drawString("Chaser 2 Health: " + SlowChaser2.health, 0, 120);
//        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 160);
//        g.drawString("Chaser 1 Location: " + SlowChaser2.x + ", " + SlowChaser2.y, 0, 200);
//        g.drawString("Chaser 2 Location: " + SlowChaser.x + ", " + SlowChaser.y, 0, 240);
//        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 280);
//        g.drawString("Fist direction: " + Fist.direction, 0, 320);

    }

    @Override
    public void tick() {

    }
}
