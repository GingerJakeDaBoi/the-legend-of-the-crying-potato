package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Actors.Hitbox;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedHorizontal2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;

import javax.swing.*;
import java.awt.*;

public class SpeedFork extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    final Image nextUpLvl = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    final Image nextRightLvl = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    String playerDirection;
    BadPotato enemy1,enemy2;
    boolean finished = false;
    boolean switching;
    boolean switchUp;
    boolean switchRight;
    final int nextUpLvlX = 0;
    int nextUpLvlY = -GamePanel.height;
    int nextRightLvlX = GamePanel.width;
    final int nextRightLvlY = 0;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the top part of the bottom right side
    final Hitbox hitbox1a = new Hitbox(780,621,GamePanel.width,1,"up");
    //now the left part
    final Hitbox hitbox1b = new Hitbox(773,621,1,GamePanel.height,"left");
    //Hitbox for the bottom part of the top right side
    final Hitbox hitbox2a = new Hitbox(790,225,GamePanel.width,1,"down");
    //now the left part
    final Hitbox hitbox2b = new Hitbox(773,0,1,205,"left");
    //hitbox for the right side
    final Hitbox hitbox3 = new Hitbox(0,0,276,861,"right");


    public SpeedFork() {
        super(gsm);
    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();

        enemy1 = new BadPotato(450, 50, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
        enemy2 = new BadPotato(1350, 300, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextUpLvl, nextUpLvlX, nextUpLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextRightLvl, nextRightLvlX, nextRightLvlY, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right") && !switching) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left") && !switching) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if(enemy1.isEnabled()) {
            g.drawImage(enemy1.getAsset(), enemy1.getX(), enemy1.getY(), enemy1.getWidth(), enemy1.getHeight(), null);
        }
        if(enemy2.isEnabled()) {
            g.drawImage(enemy2.getAsset(), enemy2.getX(), enemy2.getY(), enemy2.getWidth(), enemy2.getHeight(), null);
        }

        fistUI(g);
        heartUI(g);

        debugInfo(g);

    }

    @Override
    public void tick() {
        if (PlayerController.righting) {
            playerDirection = "right";
        }
        if (PlayerController.lefting) {
            playerDirection = "left";
        }

        PlayerController.tick();
        if(enemy1.isEnabled()) {
            enemy1.tick();
        }
        if(enemy2.isEnabled()) {
            enemy2.tick();
        }

        if (enemy1.isDead() && enemy2.isDead()) {
            finished = true;
        }

        hitbox1a.tick();
        hitbox1b.tick();
        hitbox2a.tick();
        hitbox2b.tick();
        hitbox3.tick();

        if (finished) {
            if (PlayerController.y == 0) {
                switching = true;
                switchUp = true;
                switchRight = false;
            }
            if (PlayerController.x == GamePanel.width - PlayerController.width) {
                switching = true;
                switchUp = false;
                switchRight = true;
            }
            if (switching) {
                if (switchUp) {
                    PlayerController.y -= 5;

                    if (nextUpLvlY < 0) {
                        currentLvlY += 3;
                        nextUpLvlY += 3;
                    } else {
                        GameStateManager.setState(new SpeedVertical1());
                    }
                }
                if (switchRight) {
                    PlayerController.x += 5;

                    if (nextRightLvlX > 0) {
                        currentLvlX -= 6;
                        nextRightLvlX -= 6;
                    } else {
                        GameStateManager.setState(new SpeedHorizontal2());
                    }
                }
            }
        }
    }
}
