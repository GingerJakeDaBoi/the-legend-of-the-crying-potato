package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Chaser;
import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TestLevel extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/FarmTempIcon.png").getImage();
    Image fist;

    public TestLevel() {
        super(gsm);
        if (!GamePanel.gameStarted) {
            PlayerController.spawn(500, 500, 128, 128);
            Chaser.spawn(400, 400, 128, 128);
        }
        PlayerController.enable();
        Chaser.disable();
        GamePanel.gameStarted = true;

    }

    @Override
    public void init() {


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(PlayerController.playerAsset, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if (Chaser.enabled) {
            g.drawImage(chaser, Chaser.x, Chaser.y, Chaser.width, Chaser.height, null);
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

        g.drawString("Chaser Health: " + Chaser.health, 0, 80);
        g.drawString("Player Location: " + PlayerController.x + ", " + PlayerController.y, 0, 120);
        g.drawString("Chaser Location: " + Chaser.x + ", " + Chaser.y, 0, 160);
        g.drawString("Fist Location: " + Fist.x + ", " + Fist.y, 0, 200);
        g.drawString("Fist direction: " + Fist.direction, 0, 240);


        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
        }


    }

    @Override
    public void tick() {

    }
}
