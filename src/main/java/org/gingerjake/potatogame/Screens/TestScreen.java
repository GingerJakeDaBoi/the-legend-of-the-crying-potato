package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.Actors.Dummy.Enemy;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Potato;
import org.gingerjake.potatogame.GameState;

import java.awt.*;
import java.util.Objects;


public class TestScreen extends GameState {

    public TestScreen() {
        super(gsm);
        Enemy.spawn(50, 50);

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Potato.PotatoAsset, Potato.X, Potato.Y, 128, 128, null);


        if (Fist.visible) {
            if (Objects.equals(Fist.direction, "left")) {
                g.drawImage(Fist.FistL, Fist.X, Fist.Y, Fist.width, Fist.height, null);
            }
            if (Objects.equals(Fist.direction, "right")) {
                g.drawImage(Fist.FistR, Fist.X, Fist.Y, Fist.width, Fist.height, null);
            }
            if (Objects.equals(Fist.direction, "up")) {
                g.drawImage(Fist.FistU, Fist.X, Fist.Y, Fist.width, Fist.height, null);
            }
            if (Objects.equals(Fist.direction, "down")) {
                g.drawImage(Fist.FistD, Fist.X, Fist.Y, Fist.width, Fist.height, null);
            }
        }
        g.drawImage(Enemy.EnemyAsset, Enemy.X, Enemy.Y, Enemy.width, Enemy.height, null);
        if (Enemy.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (Enemy.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (Enemy.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }



    }

    @Override
    public void tick() {

    }
}
