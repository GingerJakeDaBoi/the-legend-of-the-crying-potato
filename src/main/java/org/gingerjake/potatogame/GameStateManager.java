package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.ControlMenu;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.Menus.StartScreen;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEntrance;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedFork;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedCurve1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedHorizontal1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;
import org.gingerjake.potatogame.Levels.TestSpace;

import java.awt.*;
import java.util.Stack;

public class GameStateManager extends KeyListener {
    public static String version = "RMAKE-inDev";
    private static String currentLevel;
    public static Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
        states.push(new StartScreen());
    }

    public static void setState(GameState state) {
        states.push(state);
    }

    public static String getState() {
        return states.peek().getClass().getSimpleName();
    }

    public static void pause() {
        //TODO: Record X and Y of enemy and player, and then resume from that point in case levels set those values themselves.
        PauseMenu.paused = true;
        currentLevel = getState();

        setState(new PauseMenu());
        PlayerController.disable();
        Enemy.disable();
    }

    public static void resume() {
        PauseMenu.paused = false;
        ControlMenu.enabled = false;
        PlayerController.enable();

        switch (currentLevel) {
            case "StartScreen" -> setState(new StartScreen());
            case "TestSpace" -> setState(new TestSpace());
            case "PauseMenu" -> setState(new PauseMenu());
            case "ControlMenu" -> setState(new ControlMenu());
            case "SpeedFork" -> setState(new SpeedFork());
            case "SpeedEntrance" -> setState(new SpeedEntrance());
            case "SpeedVertical1" -> setState(new SpeedVertical1());
            case "SpeedHorizontal1" -> setState(new SpeedHorizontal1());
            case "SpeedCurve1" -> setState(new SpeedCurve1());
            default -> {
                System.out.println("Error: Level not specified in GameStateManager. If you are a player of the game, please report this to the developer!");
                System.exit(4108);
            }
        }
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}