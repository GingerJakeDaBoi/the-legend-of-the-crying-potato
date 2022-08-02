package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.Enemy;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.PotatoFarmBG;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Potato;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import java.awt.*;
import java.util.Objects;

public class PotatoFarmChase extends GameState {

    public static boolean completed;

    public PotatoFarmChase() {
        super(gsm);
        PotatoFarmBG.X = 0;
        Potato.speed = 15;
        Enemy.spawn(50, 50);
        new Thread(Enemy::chase).start();

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(PotatoFarmBG.Background, PotatoFarmBG.X, 0, 12 *PotatoFarmBG.width, 2 * PotatoFarmBG.height, null);

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
        if (Enemy.spawned) {
            g.drawImage(Enemy.EnemyAsset, Enemy.X, Enemy.Y, Enemy.width, Enemy.height, null);
        }

        if (Potato.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (Potato.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (Potato.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if(PotatoFarmBG.X <= -2400) {
            completed = true;
            Potato.speed = 10;
            Enemy.spawned = false;
            GameStateManager.setState(new WorldHub());
        }

        if(Potato.X > GamePanel.width - 200) {
            PotatoFarmBG.X -= 5;
            Potato.X -= 5;
            Enemy.X -= 5;
        }



    }

    @Override
    public void tick() {

    }
}
