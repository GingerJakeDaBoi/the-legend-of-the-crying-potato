package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.Actors.Fist;
import org.gingerjake.potatogame.Actors.Potato;
import org.gingerjake.potatogame.GamePanel;
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

        g.drawImage(Potato.PotatoAsset, Potato.X, Potato.Y, 96, 96, null);

        //draw health in the corner
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Health: " + Potato.health, 0, (int) (GamePanel.height * .945));

    }

    @Override
    public void tick() {

    }
}
