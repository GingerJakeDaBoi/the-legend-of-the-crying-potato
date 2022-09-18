package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends GameState {

    public StartScreen() {
        super(gsm);
    }

    public void init() {

    }


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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Enter to Start", 0, GamePanel.height - 12);



    }

    public void tick() {

    }
}

