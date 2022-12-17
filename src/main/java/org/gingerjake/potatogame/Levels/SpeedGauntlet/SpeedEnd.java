package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.BossPrototypeFinal;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedVertical2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedHorizontal1;

import javax.swing.*;
import java.awt.*;

public class SpeedEnd extends GameState {
    final Image background = new ImageIcon("Assets/SpeedGauntlet/End.png").getImage();
    final Image boots = new ImageIcon("Assets/Dummy/potion.png").getImage();
    final int bootsX = 900;
    final int bootsY = 400;
    final int bootsWidth = 64;
    final int bootsHeight = 64;
    String playerDirection;
    public static boolean completed = false;

    public SpeedEnd() {
        super(gsm);
    }

    @Override
    public void init() {
        playerDirection = "right";
        PlayerController.enable();
        GamePanel.gameStarted = true;

        if(SpeedVertical2.finished) {
            PlayerController.x = GamePanel.height / 2;
            PlayerController.y = PlayerController.height + 150;
        } else if(SpeedHorizontal1.finished) {
            PlayerController.x = PlayerController.width + 150;
            PlayerController.y = GamePanel.height / 2;
        } else {
            PlayerController.x = GamePanel.width / 2;
            PlayerController.y = PlayerController.height + 150;
        }
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(background, 0, 0, GamePanel.width, GamePanel.height, null);
        g.drawImage(boots, bootsX, bootsY, bootsWidth, bootsHeight, null);

        if (playerDirection.equals("right")) {
            g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
        } else if (playerDirection.equals("left")) {
            g.drawImage(PlayerController.playerLeft, PlayerController.x, PlayerController.y,
                    PlayerController.width, PlayerController.height, null);
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

        if (PlayerController.x + PlayerController.width > bootsX && PlayerController.x < bootsX + bootsWidth && PlayerController.y + PlayerController.height > bootsY && PlayerController.y < bootsY + bootsHeight) {
            PlayerController.giveSpeed();
            PlayerController.giveDamage();
            PlayerController.giveHeart();
            PlayerController.health = 4;
            GameStateManager.setState(new BossPrototypeFinal());
        }

    }
}
