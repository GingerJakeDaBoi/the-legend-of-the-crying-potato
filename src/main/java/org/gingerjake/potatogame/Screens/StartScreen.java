package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends GameState {
    Image PotatoMain = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();

    public StartScreen() {
        super(gsm);
    }

    public void init() {

    }


    public void draw(Graphics g) {
        g.setColor(new Color(196, 103, 227, 255));
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Press Enter to start", 0, GamePanel.height - 5);
        g.drawString("The Legend of the Crying Potato", 7, 49);

        g.drawImage(PotatoMain, (int) (GamePanel.width * .65), (int) (GamePanel.height * .5), 256, 256, null);



    }

    public void tick() {

    }
}

