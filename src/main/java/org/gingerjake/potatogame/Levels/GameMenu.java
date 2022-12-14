package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.*;
import org.gingerjake.potatogame.Actors.Player.PlayerController;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends GameState {
    public static boolean isPaused;
    public static boolean isGameOver;
    public static boolean optionsOpened;
    int randomImage;
    public static int selectedOptionH;
    public static int selectedOptionV;

    public GameMenu() {
        super(gsm);
    }

    @Override
    public void init() {
        PlayerController.disable();
        isPaused = true;
        randomImage = (int) (Math.random() * 3 + 1);
        selectedOptionH = 0;
        optionsOpened = false;
    }

    @Override
    public void draw(Graphics g) {
        if(!optionsOpened) {
            switch (randomImage) {
                case 1 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu1.png").getImage(), 0, 0, GamePanel.width, GamePanel.height, null);
                case 2 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu2.jpg").getImage(), 0, 0, GamePanel.width, GamePanel.height, null);
                case 3 -> g.drawImage(new ImageIcon("Assets/Dummy/Menu3.png").getImage(), 0, 0, GamePanel.width, GamePanel.height, null);
            }
        } else {
            g.setColor(new Color(120, 120, 120, 255));
            g.drawRect(0,0,GamePanel.width,GamePanel.height);
            for (int i = 85; i < GamePanel.height - 85; i += 50) {
                g.setColor(new Color(128, 128, 128, 255));
                g.fillRect(0,i,GamePanel.width,8);
            }
            g.fillRect(GamePanel.width/2,0,8,GamePanel.height);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        //TOPBAR
        g.setColor(new Color(80, 80, 80, 255));
        g.fillRect(0, 0, GamePanel.width, 85);

        g.setColor(Color.WHITE);
        if (isPaused) {
            g.drawString("Potato Game: VERSION " + GameStateManager.version, 20, 60);
        } else if (isGameOver) {
            g.drawString("GAME OVER", GamePanel.width / 2 - 150, GamePanel.height / 2);
        }

        //SUPERBAR (bottom)
        g.setColor(new Color(80, 80, 80, 255));
        g.fillRect(0, GamePanel.height - 85, GamePanel.width, 85);

        g.setColor(Color.WHITE);

        g.setColor(new Color(100, 100, 100, 255));
        if(selectedOptionV == 0) {
            switch (selectedOptionH) {
                case 0 -> g.fillRect(0, GamePanel.height - 85, 240, GamePanel.height);
                case 1 -> g.fillRect(240, GamePanel.height - 85, 240, GamePanel.height);
                case 2 -> g.fillRect(480, GamePanel.height - 85, 160, GamePanel.height);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Resume", 20, GamePanel.height - 25);
        g.drawString("Options", 260, GamePanel.height - 25);
        g.drawString("Quit", 500, GamePanel.height - 25);

        g.setColor(Color.BLACK);
        g.drawString("H: " + selectedOptionH + "V: " + selectedOptionV,GamePanel.width/2,GamePanel.height/2);
    }

    @Override
    public void tick() {
        //logic?
    }

    public static void select() {
        if(!optionsOpened || selectedOptionV == 0) {
            switch (selectedOptionH) {
                case 0 -> Controls.resume();
                case 1 -> {
                    optionsOpened = true;
                    selectedOptionV = Settings.settings.length;
                    selectedOptionH = 0;
                    System.out.println("Options length set to: " + selectedOptionV);
                }
                case 2 -> {
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }
        }
    }

    public static void menuUp() {
        if(selectedOptionV < Settings.settings.length && optionsOpened) {
            selectedOptionV++;
        }
    }

    public static void menuDown() {
        if(selectedOptionV > 0 && optionsOpened) {
            selectedOptionV--;
        }
    }

    public static void menuLeft() {
        if (selectedOptionH > 0) {
            selectedOptionH--;
        }
    }

    public static void menuRight() {
        if (selectedOptionH < 2) {
            selectedOptionH++;
        }
    }
}