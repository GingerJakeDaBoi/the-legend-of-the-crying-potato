package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class LevelComplete extends GameState {
    public static boolean complete;
    public static boolean bossKilled;
    public static int earned;

    public LevelComplete() {
        super(gsm);

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        //draw the level complete screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("Level Complete", 0, 40);


        g.drawString("Completion", 0, 300);
        g.drawString("Boss killed", 0, 400);

        g.drawString("Press Enter to Continue", 0, 800);
        //if earned is 1, draw one heart. if earned is 2, draw two hearts. if earned is 3, draw three hearts.

        if (complete) {
            g.drawImage(Health.playerHeart, 280, 260, 50, 50, null);

        }
        if (bossKilled) {
            g.drawImage(Health.playerHeart, 270, 360, 50, 50, null);

        }


    }

    @Override
    public void tick() {

    }
}
