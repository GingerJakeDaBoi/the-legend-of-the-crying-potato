package org.gingerjake.potatogame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("Potato Game");
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(GamePanel.width, GamePanel.height);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);


        gameWindow.add(new GamePanel(), BorderLayout.CENTER);
        gameWindow.setVisible(true);

    }

}