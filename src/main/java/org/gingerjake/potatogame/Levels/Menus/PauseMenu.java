package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GameState {
    public static boolean paused;
    public static int Selection;

    public PauseMenu() {
        super(gsm);
        paused = true;
        Selection = 0;
        ControlMenu.enabled = false;

    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(196, 103, 227, 255));
        g.drawImage(new ImageIcon("Assets/Dummy/PotatoFarmTestBG.jpg").getImage(),0, 0, GamePanel.width, GamePanel.height,null);
        g.setColor(new Color(86, 34, 232, 255));
        g.fillRect(0, 0, GamePanel.width, 60);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString(GameStateManager.version,61,48);
        g.drawImage(new ImageIcon("Assets/GUI/Heart.png").getImage(), 8, 3, 50, 50, null);

        g.setColor(new Color(90, 43, 115, 255));
        g.fillRect(0, GamePanel.height - 60, GamePanel.width, 60);

        //if Selection is 0, draw a checkmark next to resume. if Selection is 1, draw a checkmark next to exit.
        g.setColor(new Color(115, 55, 145, 255));
        if(Selection == 0) {
            g.fillRect(0, GamePanel.height - 60, 200, 60);
        }
        if(Selection == 1) {
            g.fillRect(215, GamePanel.height -60, 215, 60);
        }
        if(Selection == 2) {
            g.fillRect(445, GamePanel.height - 60, 107, 60);
        }

        g.setColor(Color.WHITE);
        g.drawString("Resume", 0, GamePanel.height - 12);
        g.drawString("Controls", 220, GamePanel.height -12);
        g.drawString("Exit", 450, GamePanel.height - 12);




    }

    @Override
    public void tick() {

    }
}
