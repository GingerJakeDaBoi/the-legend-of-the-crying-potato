package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.Controls;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class ControlMenu extends GameState {
    Image select = new ImageIcon("Assets/GUI/Heart.png").getImage();
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
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        g.drawString("CONTROLS", 0, 40);
        if(Controls.controlMode == 1) {
            g.drawString("IJKL to move", 0, 90);
        }
        if(Controls.controlMode == 0) {
            g.drawString("Arrow keys to move", 0, 90);
        }

        g.drawString("Move with arrow keys", 0, 500);
        g.drawString("Move with IJKL", 0, 550);

        if(Selection == 0) {
            g.drawImage(select, 520, 455, 50, 50, null);
        }
        if(Selection == 1) {
            g.drawImage(select, 360, 505, 50, 50, null);
        }

    }

    @Override
    public void tick() {

    }
}

