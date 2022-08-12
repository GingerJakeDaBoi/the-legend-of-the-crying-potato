package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class TestLevel extends GameState {
    public Image player = new ImageIcon("Assets/Dummy/Green.png").getImage();
    public TestLevel() {
        super(gsm);
        if(!GamePanel.gameStarted) {
            PlayerController.spawn(500,500,128,128);
        }
        PlayerController.enable();
        GamePanel.gameStarted = true;

    }

    @Override
    public void init() {


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(player, PlayerController.x, PlayerController.y,PlayerController.width,PlayerController.height, null);

        if (PlayerController.health == 3) {
            g.drawImage(PlayerController.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeart, 70, 0, 32, 32, null);
        }
        if (PlayerController.health == 2) {
            g.drawImage(PlayerController.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (PlayerController.health == 1) {
            g.drawImage(PlayerController.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(PlayerController.playerHeartBroken, 70, 0, 32, 32, null);
        }



    }

    @Override
    public void tick() {

    }
}
