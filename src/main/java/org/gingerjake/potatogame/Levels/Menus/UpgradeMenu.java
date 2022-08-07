package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class UpgradeMenu extends GameState {
    public static boolean upgradeOpen;
    public static int Selection;
    public UpgradeMenu() {
        super(gsm);
        PauseMenu.paused = false;
        upgradeOpen = true;
    }
    @Override
    public void init() {

    }
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("Upgrade Menu", 0, 40);

        g.drawString("Speed", 0, 500);
        g.drawString("Health", 0, 550);
        g.drawString("Power", 0, 600);

        g.drawString(Player.pointsEarned + " points earned", GamePanel.width - 373, 40);

        if(Selection == 0) {
            g.drawImage(Health.playerHeart, 150, 455, 50, 50, null);
        }
        if(Selection == 1) {
            g.drawImage(Health.playerHeart, 150, 505, 50, 50, null);
        }
        if(Selection == 2) {
            g.drawImage(Health.playerHeart, 150, 555, 50, 50, null);
        }

    }

    @Override
    public void tick() {

    }

}

