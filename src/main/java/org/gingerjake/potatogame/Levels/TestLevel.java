package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.Chaser;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class TestLevel extends GameState {
    Image player = new ImageIcon("Assets/Dummy/Green.png").getImage();
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image chaser = new ImageIcon("Assets/Dummy/Red.png").getImage();
    public TestLevel() {
        super(gsm);
        if(!GamePanel.gameStarted) {
            PlayerController.spawn(500,500,128,128);
            Chaser.spawn(400,400,128,128);
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

        g.drawImage(player, PlayerController.x, PlayerController.y,PlayerController.width,PlayerController.height, null);

        g.drawImage(chaser, Chaser.x, Chaser.y,Chaser.width,Chaser.height, null);

        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 2, 0, 32, 32, null);
            g.drawImage(playerHeart, 36, 0, 32, 32, null);
            g.drawImage(playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 2, 0, 32, 32, null);
            g.drawImage(playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(playerHeartBroken, 70, 0, 32, 32, null);
        }



    }

    @Override
    public void tick() {

    }
}
