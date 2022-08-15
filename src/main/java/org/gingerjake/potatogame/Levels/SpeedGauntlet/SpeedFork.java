package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemies.SlowChaser;
import org.gingerjake.potatogame.Actors.Enemies.SlowChaser2;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpeedFork extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/Red.png").getImage();
    Image background = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();

    Image fist;

    public SpeedFork() {
        super(gsm);

        SlowChaser.health = 3;
        SlowChaser.spawn(400, SlowChaser.height,96,96);
        SlowChaser.enable();
        new Thread(SlowChaser::chase).start();

        SlowChaser2.health = 3;
        SlowChaser2.spawn(900, 300,96,96);
        SlowChaser2.enable();
        new Thread(SlowChaser2::chase).start();

        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();

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
        if(SlowChaser2.enabled) {
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

        if (PlayerController.x > GamePanel.width - 990) {
            if (!(PlayerController.y > 210)) {
                System.out.println("1");
                PlayerController.y = 210;
            }
            if (PlayerController.y > 425) {
                System.out.println("2");
                PlayerController.y = 425;
            }

        }
        if (PlayerController.x > GamePanel.width - 1000) {
            if (!(PlayerController.y > 205)) {
                System.out.println("3");
                PlayerController.x = GamePanel.width - 1000;
            }
        }
        if(PlayerController.x > GamePanel.width - 1000){
            if(PlayerController.y > 425){
                System.out.println("4");
                PlayerController.x = GamePanel.width - 1000;
            }
        }

        if (PlayerController.x <= 255) {
            PlayerController.x = 255;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.y < 0) {
            PlayerController.y = 0;
        }





    }

    @Override
    public void tick() {

    }
}
