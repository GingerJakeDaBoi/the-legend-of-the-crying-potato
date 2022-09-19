package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class ControlMenu extends GameState {
    public static int Selection;
    public static boolean enabled;

    public ControlMenu() {
        super(gsm);
        Selection = 0;
        ControlMenu.enabled = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(new ImageIcon("Assets/Dummy/PotatoFarmTestBG.jpg").getImage(),0, 0, GamePanel.width, GamePanel.height,null);
        g.setColor(new Color(86, 34, 232, 255));
        g.fillRect(0, 0, GamePanel.width, 60);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString(GameStateManager.version,61,48);
        g.drawImage(new ImageIcon("Assets/GUI/Heart.png").getImage(), 8, 3, 50, 50, null);

        g.setColor(new Color(90, 43, 115, 255));
        g.fillRect(0, GamePanel.height - 60, GamePanel.width, 60);

        g.setColor(new Color(98, 98, 98, 255));
        g.fillRect(0, 60, GamePanel.width, GamePanel.height - 120);

        if(Selection == 0) {
            g.setColor(new Color(124, 124, 124, 255));
            g.fillRect(250, 225, 350, 300);
        }
        if(Selection == 1) {
            g.setColor(new Color(124, 124, 124, 255));
            g.fillRect(850, 225, 350, 300);
        }

        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/W.png")).getImage(), 400, 300, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/S.png")).getImage(), 400, 400, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/A.png")).getImage(), 300, 400, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/D.png")).getImage(), 500, 400, 64, 64, null);

        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/I.png")).getImage(), 1000, 300, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/K.png")).getImage(), 1000, 400, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/J.png")).getImage(), 900, 400, 64, 64, null);
        g.drawImage(new ImageIcon(("Assets/GUI/ControlMenu/Keys/L.png")).getImage(), 1100, 400, 64, 64, null);

        g.setColor(new Color(115, 55, 145, 255));
        g.fillRect(215, GamePanel.height -60, 215, 60);
        g.setColor(Color.WHITE);
        g.drawString("Resume", 0, GamePanel.height - 12);
        g.drawString("Controls", 220, GamePanel.height -12);
        g.drawString("Exit", 450, GamePanel.height - 12);

    }

    @Override
    public void tick() {

    }
}

