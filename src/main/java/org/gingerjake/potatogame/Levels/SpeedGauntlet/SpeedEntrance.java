package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class SpeedEntrance extends GameState {
    Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical.png").getImage();
    Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/PathFork.png").getImage();
    String playerDirection;
    BadPotato enemy;
    boolean finished = false;
    boolean switching;
    int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    int hitbox1X = 773;
    int hitbox1Y = 0;
    int hitbox1W = 811;
    int hitbox1H = 861;
    //hitbox for the left side
    int hitbox2X = 0;
    int hitbox2Y = 0;
    int hitbox2W = 276;
    int hitbox2H = 861;


    public SpeedEntrance() {
        super(gsm);
    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 400;
        PlayerController.y = GamePanel.height - PlayerController.height;
        PlayerController.enable();

        enemy = new BadPotato(450, 50, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl, nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right") && !switching) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left") && !switching) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if (enemy.isEnabled()) {
            g.drawImage(enemy.getAsset(), enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight(), null);
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
        if(enemy.isEnabled()) {
            enemy.tick();
        }

        if(enemy.isDead()) {
            finished = true;
        }

        if (PlayerController.x + PlayerController.width > hitbox1X && PlayerController.x < hitbox1X + hitbox1W && PlayerController.y + PlayerController.height > hitbox1Y && PlayerController.y < hitbox1Y + hitbox1H) {
            PlayerController.x = hitbox1X - PlayerController.width;
        }
        if (PlayerController.x + PlayerController.width > hitbox2X && PlayerController.x < hitbox2X + hitbox2W && PlayerController.y + PlayerController.height > hitbox2Y && PlayerController.y < hitbox2Y + hitbox2H) {
            PlayerController.x = hitbox2W;
        }

        if (finished) {
            if (PlayerController.y == 0) {
                switching = true;
            }
            if (switching) {
                if (nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    GameStateManager.setState(new SpeedFork());
                }
            }
        }

    }
}
