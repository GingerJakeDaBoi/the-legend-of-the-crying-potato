package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.Enemies.BigChase;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Levels.PotatoFarm.PotatoFarmBG;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.Menus.LevelComplete;

import java.awt.*;
import java.util.Objects;

public class PotatoFarmChase extends GameState {

    public static boolean completed;
    public static boolean bossKill;

    public PotatoFarmChase() {
        super(gsm);
        WorldHub.FarmSelected = false;
        PotatoFarmBG.X = 0;
        Player.speed = 5;
        BigChase.spawn(50, 50);
        new Thread(BigChase::chase).start();

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(PotatoFarmBG.Background, PotatoFarmBG.X, 0, 12 * PotatoFarmBG.width, 2 * PotatoFarmBG.height, null);

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
        if (BigChase.spawned) {
            g.drawImage(BigChase.EnemyAsset, BigChase.X, BigChase.Y, BigChase.width, BigChase.height, null);
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
        if (PotatoFarmBG.X <= -2400) {
            completed = true;
            Player.speed = 10;
            LevelComplete.complete = true;
            GameStateManager.setState(new LevelComplete());
            if (!BigChase.spawned) {
                LevelComplete.bossKilled = true;
                bossKill = true;
            }
            BigChase.spawned = false;

        }

        if (Player.X > GamePanel.width - 200) {
            PotatoFarmBG.X -= 5;
            Player.X -= 5;
            BigChase.X -= 5;
        }


    }

    @Override
    public void tick() {

    }
}
