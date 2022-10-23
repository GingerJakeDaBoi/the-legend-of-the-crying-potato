package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Debug.DebugLvl;
import org.gingerjake.potatogame.Levels.DemoBoss;
import org.gingerjake.potatogame.Levels.Menus.ControlMenu;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedCurve2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedHorizontal2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedVertical2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEnd;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEntrance;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedFork;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedCurve1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedHorizontal1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;
import org.gingerjake.potatogame.Levels.Debug.TestSpace;

import java.awt.*;
import java.util.Stack;

public class GameStateManager extends KeyListener {
    public static final String version = "RMAKE-inDev";
    private static String currentLevel;
    public static Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
        states.push(new PauseMenu());
        pause();
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
    }

    public static void resume() {
        PauseMenu.paused = false;
        ControlMenu.enabled = false;
        PlayerController.enable();

        if (PauseMenu.gameOver) {
            setState(new TestSpace());
            PauseMenu.gameOver = false;
        } else {
            switch (currentLevel) {
                case "TestSpace" -> setState(new TestSpace());
                case "SpeedFork" -> setState(new SpeedFork());
                case "SpeedEntrance" -> setState(new SpeedEntrance());
                case "SpeedVertical1" -> setState(new SpeedVertical1());
                case "SpeedHorizontal1" -> setState(new SpeedHorizontal1());
                case "SpeedCurve1" -> setState(new SpeedCurve1());
                case "SpeedCurve2" -> setState(new SpeedCurve2());
                case "SpeedHorizontal2" -> setState(new SpeedHorizontal2());
                case "SpeedVertical2" -> setState(new SpeedVertical2());
                case "SpeedEnd" -> setState(new SpeedEnd());
                case "DebugLvl" -> setState(new DebugLvl());
                case "DemoBoss" -> setState(new DemoBoss());
                default -> {
                    System.out.println("Level not specified in GameStateManager. Defaulting to TestSpace.");
                    setState(new TestSpace());
                }
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