package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.GUI.Checkmark;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Player;
import org.gingerjake.potatogame.Actors.Levels.World.FarmBuilding;
import org.gingerjake.potatogame.Actors.Levels.World.GameWorld;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;
import org.gingerjake.potatogame.Levels.Menus.LevelComplete;

import java.awt.*;
import java.util.logging.Level;

public class WorldHub extends GameState {

    public static boolean FarmSelected;

    public WorldHub() {
        super(gsm);
        Player.speed = 15;
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(GameWorld.gameWorld, GameWorld.X, GameWorld.Y, 4 * GamePanel.width, 4 * GamePanel.height, null);

        g.drawImage(Player.PotatoAsset, Player.X, Player.Y, 64, 64, null);

        g.drawImage(FarmBuilding.FarmBuildingAsset, FarmBuilding.X, FarmBuilding.Y, FarmBuilding.width, FarmBuilding.height, null);

        if (Player.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (Player.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (Player.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if(Player.Y > GamePanel.height - 250) {
            Player.Y -= 5;
            GameWorld.Y -= 1;
            FarmBuilding.Y -= 1;
        }
        if(Player.X > GamePanel.width - 250) {
            Player.X -= 5;
            GameWorld.X -= 1;
            FarmBuilding.X -= 1;
        }
        if(Player.Y < 200) {
            Player.Y += 5;
            GameWorld.Y += 1;
            FarmBuilding.Y += 1;
        }
        if(Player.X < 250) {
            Player.X += 5;
            GameWorld.X += 1;
            FarmBuilding.X += 1;
        }
        //if there is any blank space on the map, move the map and buildings accordingly
        if(GameWorld.X >= 0) {
            GameWorld.X = 0;
            FarmBuilding.X = 1500;
        }
        if(GameWorld.Y >= 0) {
            GameWorld.Y = 0;
            FarmBuilding.Y = 800;
        }
        if(GameWorld.X <= -(4 * GamePanel.width - GamePanel.width)) {
            GameWorld.X = -(4 * GamePanel.width - GamePanel.width);
            FarmBuilding.X = -3240;
        }
        if(GameWorld.Y <= -(4 * GamePanel.height - GamePanel.height)) {
            GameWorld.Y = -(4 * GamePanel.height - GamePanel.height);
            FarmBuilding.Y = -1780;
        }
        if(Player.X >= FarmBuilding.X && Player.X <= FarmBuilding.X + FarmBuilding.width && Player.Y >= FarmBuilding.Y && Player.Y <= FarmBuilding.Y + FarmBuilding.height) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(new Color(255, 255, 255));
            g.drawString("PotatoFarmTest", FarmBuilding.X - 100, FarmBuilding.Y - 50);
            g.drawString("Press Enter to Continue", FarmBuilding.X - 150, FarmBuilding.Y + 50);
            if(PotatoFarmChase.completed) {
                g.drawImage(Checkmark.Checkmark, FarmBuilding.X + 320, FarmBuilding.Y - 100, 64, 64, null);
            }
            FarmSelected = true;
        } else {
            FarmSelected = false;
        }

    }

    @Override
    public void tick() {

    }
}