package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Fist;
import org.gingerjake.potatogame.Actors.Player.Potato;
import org.gingerjake.potatogame.GameState;

import java.awt.*;
import java.util.Objects;


public class TestScreen extends GameState {

    public TestScreen() {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        if (Objects.equals(Fist.direction, "left")) {
            Fist.X = Fist.X - 5;
        }
        if (Objects.equals(Fist.direction, "right")) {
            Fist.X = Fist.X + 5;
        }
        if (Objects.equals(Fist.direction, "up")) {
            Fist.Y = Fist.Y - 5;
        }
        if (Objects.equals(Fist.direction, "down")) {
            Fist.Y = Fist.Y + 5;
        }
        if (Fist.visible) {
            g.drawImage(Fist.FistAsset, Fist.X, Fist.Y, 64, 64, null);

        }

        g.drawImage(Potato.PotatoAsset, Potato.X, Potato.Y, 128, 128, null);

        if(Potato.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if(Potato.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if(Potato.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }

    }

    @Override
    public void tick() {

    }
}
