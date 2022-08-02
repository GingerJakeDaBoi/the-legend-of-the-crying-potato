package org.gingerjake.potatogame.Levels;

import org.gingerjake.potatogame.Actors.GUI.Checkmark;
import org.gingerjake.potatogame.Actors.GUI.Health;
import org.gingerjake.potatogame.Actors.Player.Potato;
import org.gingerjake.potatogame.Actors.World.FarmBuilding;
import org.gingerjake.potatogame.Actors.World.GameWorld;
import org.gingerjake.potatogame.GamePanel;
import org.gingerjake.potatogame.GameState;

import java.awt.*;

public class WorldHub extends GameState {

    public static boolean FarmSelected;

    public WorldHub() {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(GameWorld.gameWorld, GameWorld.X, GameWorld.Y, 4 * GamePanel.width, 4 * GamePanel.height, null);

        g.drawImage(Potato.PotatoAsset, Potato.X, Potato.Y, 64, 64, null);

        g.drawImage(FarmBuilding.FarmBuildingAsset, FarmBuilding.X, FarmBuilding.Y, FarmBuilding.width, FarmBuilding.height, null);

        if (Potato.health == 3) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 70, 0, 32, 32, null);
        }
        if (Potato.health == 2) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeart, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if (Potato.health == 1) {
            g.drawImage(Health.playerHeart, 2, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 36, 0, 32, 32, null);
            g.drawImage(Health.playerHeartBroken, 70, 0, 32, 32, null);
        }
        if(Potato.Y > GamePanel.height - 250) {
            Potato.Y -= 10;
            GameWorld.Y -= 20;
            FarmBuilding.Y -= 20;
        }
        if(Potato.X > GamePanel.width - 250) {
            Potato.X -= 10;
            GameWorld.X -= 20;
            FarmBuilding.X -= 20;
        }
        if(Potato.Y < 200) {
            Potato.Y += 10;
            GameWorld.Y += 20;
            FarmBuilding.Y += 20;
        }
        if(Potato.X < 250) {
            Potato.X += 10;
            GameWorld.X += 20;
            FarmBuilding.X += 20;
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
        if(Potato.X >= FarmBuilding.X && Potato.X <= FarmBuilding.X + FarmBuilding.width && Potato.Y >= FarmBuilding.Y && Potato.Y <= FarmBuilding.Y + FarmBuilding.height) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(new Color(255, 255, 255));
            g.drawString("The Potato Farm", FarmBuilding.X - 100, FarmBuilding.Y - 50);
            g.drawString("Press Enter to Continue", FarmBuilding.X - 150, FarmBuilding.Y + 50);
            if(PotatoFarm.completed) {
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