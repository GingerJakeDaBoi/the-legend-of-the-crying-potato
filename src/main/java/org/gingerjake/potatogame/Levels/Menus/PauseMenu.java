package org.gingerjake.potatogame.Levels.Menus;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends GameState {
    Image heartSelection;
    public static boolean paused;
    public static int Selection;
    public static String previousState;

    public PauseMenu() {
        super(gsm);
        paused = true;
        PlayerController.disable();
        org.gingerjake.potatogame.Actors.Enemies.Chaser.disable();
        heartSelection = new ImageIcon("Assets/GUI/Heart.png").getImage();
        Selection = 0;

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
        g.drawString("PAUSED", 0, 40);

        g.drawString("Resume", 0, 500);
        g.drawString("Exit", 0, 550);

        //if Selection is 0, draw a checkmark next to resume. if Selection is 1, draw a checkmark next to exit. if Selection is 2, draw a checkmark next to save.
        if(Selection == 0) {
            g.drawImage(heartSelection, 200, 455, 50, 50, null);
        }
        if(Selection == 1) {
            g.drawImage(heartSelection, 100, 505, 50, 50, null);
        }


    }

    @Override
    public void tick() {

    }
}
