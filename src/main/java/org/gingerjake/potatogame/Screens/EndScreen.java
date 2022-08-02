package org.gingerjake.potatogame.Screens;

import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends GameState {
    Image PotatoMain = new ImageIcon("Assets/Potato/PotatoMain.png").getImage();

    public EndScreen() {
        super(gsm);
    }

    public void init() {

    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("You Lost!", 2, GamePanel.height - 5);

        g.drawImage(PotatoMain, (int) (GamePanel.width * .65), (int) (GamePanel.height * .5), 256, 256, null);

    }

    public void tick() {

    }
}

