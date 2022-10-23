package org.gingerjake.potatogame.Levels.SpeedGauntlet.Up;

import org.gingerjake.potatogame.Actors.Enemies.BadPotato;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEnd;

import javax.swing.*;
import java.awt.*;

public class SpeedHorizontal1 extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/Horizontal.png").getImage();
    final Image nextLvl = new ImageIcon("Assets/SpeedGauntlet/End.png").getImage();

    String playerDirection;
    BadPotato enemy;
    public static boolean finished = false;
    boolean switching;
    int nextLvlX = GamePanel.width;
    final int nextLvlY = 0;
    int currentLvlX = 0;
    final int currentLvlY = 0;
    //Hitbox for the top part of the bottom left side
    final int hitbox1X = 0;
    final int hitbox1Y = 220;
    final int hitbox1W = GamePanel.width;
    final int hitbox1H = 1;
    //Hitbox for the bottom part of the top left side
    final int hitbox2X = 0;
    final int hitbox2Y = 622;
    final int hitbox2W = GamePanel.width;
    final int hitbox2H = 1;



    public SpeedHorizontal1() {
        super(gsm);

    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.x = 115;
        PlayerController.y = 300;
        PlayerController.enable();

        enemy = new BadPotato(1350, 300, 64, 64, 5,
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
            PlayerController.y = 220;
        }
        if (PlayerController.x + PlayerController.width > hitbox2X && PlayerController.x < hitbox2X + hitbox2W && PlayerController.y + PlayerController.height > hitbox2Y && PlayerController.y < hitbox2Y + hitbox2H) {
            PlayerController.y = 622 - PlayerController.height;
        }

        if(finished) {
            if(PlayerController.x == GamePanel.width - PlayerController.width) {
                switching = true;
            }
            if(switching) {
                if(nextLvlX > 0) {
                    currentLvlX -= 6;
                    nextLvlX -= 6;
                } else {
                    GameStateManager.setState(new SpeedEnd());
                }
            }
        }

    }
}
