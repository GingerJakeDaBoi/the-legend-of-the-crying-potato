package org.gingerjake.potatogame.Levels.SpeedGauntlet.Right;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEnd;

import javax.swing.*;
import java.awt.*;

public class SpeedVertical2 extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Vertical2.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/End.png").getImage();
    String playerDirection;
    BadPotato enemy;
    public static boolean finished = false;
    boolean switching;
    final int nextLvlX = 0;
    int nextLvlY = -GamePanel.height;
    final int currentLvlX = 0;
    int currentLvlY = 0;
    //Hitbox for the right side
    final int hitbox1X = 1310;
    final int hitbox1Y = 0;
    final int hitbox1W = 811;
    final int hitbox1H = 861;
    //hitbox for the left side
    final int hitbox2X = 0;
    final int hitbox2Y = 0;
    final int hitbox2W = 813;
    final int hitbox2H = 861;



    public SpeedVertical2() {
        super(gsm);

    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 985;
        PlayerController.y = 600;
        PlayerController.enable();

        enemy = new BadPotato(1000, 45, 64, 64, 5,
                1, 1, new ImageIcon("Assets/Dummy/Red.png").getImage());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, currentLvlX, currentLvlY, GamePanel.width, GamePanel.height, null);
        g.drawImage(nextLvl,nextLvlX, nextLvlY, GamePanel.width, GamePanel.height, null);

        if (playerDirection.equals("right") && !switching) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left") && !switching) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        }

        if(enemy.isEnabled()) {
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

        if(finished) {
            if(PlayerController.y == 0) {
                switching = true;
            }
            if(switching) {
                if(nextLvlY < 0) {
                    currentLvlY += 3;
                    nextLvlY += 3;
                } else {
                    GameStateManager.setState(new SpeedEnd());
                }
            }
        }
    }
}
