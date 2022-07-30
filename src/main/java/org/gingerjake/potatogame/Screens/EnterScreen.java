package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class EnterScreen extends GameState {
    Image PotatoMain = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();

    public EnterScreen(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {

    }


    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Enter Pressed!", 0, (int) (GamePanel.height * .945));


        g.drawImage(PotatoMain, (int) (GamePanel.width * .65), (int) (GamePanel.height * .5), 256, 256, null);

    }

    public void tick() {

    }
}

