package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class TestLevel extends GameState {
    public Image player = new ImageIcon("Assets/Dummy/Green.png").getImage();
    public TestLevel() {
        super(gsm);
        PlayerController.x = 500;
        PlayerController.y = 500;

    }

    @Override
    public void init() {


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(player, PlayerController.x, PlayerController.y, null);

        if (PlayerController.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (PlayerController.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (PlayerController.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }



    }

    @Override
    public void tick() {

    }
}
