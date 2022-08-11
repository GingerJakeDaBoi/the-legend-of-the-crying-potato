package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.GenericEnemy;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.GameState;

import java.awt.*;
import java.util.Objects;

public class TestLevel extends GameState {

    public TestLevel() {
        super(gsm);
        Player.X = 500;
        Player.Y = 500;
        GenericEnemy.spawn(800,800);

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Player.PotatoAsset, Player.X, Player.Y, 128, 128, null);

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
        if(GenericEnemy.spawned){
            g.drawImage(GenericEnemy.EnemyAsset, GenericEnemy.X, GenericEnemy.Y, GenericEnemy.width, GenericEnemy.height, null);
        }
        if (Player.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (Player.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (Player.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        g.drawString("Enemy Health: " + GenericEnemy.health, 0, 100);



    }

    @Override
    public void tick() {

    }
}
