package org.gingerjake.potatogame.Levels.SpeedGauntlet;

import org.gingerjake.potatogame.Actors.Player.Attacks.Fist;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.GameStateManager;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedVertical2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedHorizontal1;
import org.gingerjake.potatogame.Levels.Debug.TestSpace;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SpeedEnd extends GameState {
    Image playerHeart = new ImageIcon("Assets/GUI/Heart.png").getImage();
    Image playerHeartBroken = new ImageIcon("Assets/GUI/HeartBroken.png").getImage();
    Image background = new ImageIcon("Assets/SpeedGauntlet/End.png").getImage();
    Image boots = new ImageIcon("Assets/Dummy/rock.jpg").getImage();
    Image fist;
    int bootsX = 900;
    int bootsY = 400;
    int bootsWidth = 64;
    int bootsHeight = 64;
    public static boolean completed = false;

    public SpeedEnd() {
        super(gsm);
    }

    @Override
    public void init() {
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

        g.drawImage(PlayerController.playerRight, PlayerController.x, PlayerController.y, PlayerController.width, PlayerController.height, null);

        if (Fist.visible) {
            if (Objects.equals(Fist.direction, "left")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistL.png").getImage();
            }
            if (Objects.equals(Fist.direction, "right")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistR.png").getImage();
            }
            if (Objects.equals(Fist.direction, "up")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistU.png").getImage();
            }
            if (Objects.equals(Fist.direction, "down")) {
                fist = new ImageIcon("Assets/Attacks/Fist/FistD.png").getImage();
            }
            g.drawImage(fist, Fist.x, Fist.y, Fist.width, Fist.height, null);
        }

        if (PlayerController.health == 4) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            g.drawImage(playerHeart, 156, 0, 48, 48, null);
        }
        if (PlayerController.health == 3) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeart, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 2) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeart, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }
        if (PlayerController.health == 1) {
            g.drawImage(playerHeart, 3, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 54, 0, 48, 48, null);
            g.drawImage(playerHeartBroken, 105, 0, 48, 48, null);
            if (PlayerController.heartGiven) {
                g.drawImage(playerHeartBroken, 156, 0, 48, 48, null);
            }
        }

        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if (PlayerController.x < 0) {
            PlayerController.x = 0;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.y < 0) {
            PlayerController.y = 0;
        }

        if (PlayerController.x + PlayerController.width > bootsX && PlayerController.x < bootsX + bootsWidth && PlayerController.y + PlayerController.height > bootsY && PlayerController.y < bootsY + bootsHeight) {
            new Thread(PlayerController::giveSpeed).start();
            GameStateManager.setState(new TestSpace());
            completed = true;
        }

        GameState.debugInfo(g);


    }

    @Override
    public void tick() {

        if (PlayerController.x > GamePanel.width - PlayerController.width) {
            PlayerController.x = GamePanel.width - PlayerController.width;
        }
        if (PlayerController.x < 0) {
            PlayerController.x = 0;
        }
        if (PlayerController.y > GamePanel.height - PlayerController.height) {
            PlayerController.y = GamePanel.height - PlayerController.height;
        }
        if (PlayerController.y < 0) {
            PlayerController.y = 0;
        }

        if (PlayerController.x + PlayerController.width > bootsX && PlayerController.x < bootsX + bootsWidth && PlayerController.y + PlayerController.height > bootsY && PlayerController.y < bootsY + bootsHeight) {
            new Thread(PlayerController::giveSpeed).start();
            GameStateManager.setState(new TestSpace());
            completed = true;
        }

    }
}
