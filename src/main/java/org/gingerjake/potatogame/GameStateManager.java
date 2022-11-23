package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.BossPrototypeFinal;
import org.gingerjake.potatogame.Levels.Debug.DebugLvl;
import org.gingerjake.potatogame.Levels.Debug.TestSpace;
import org.gingerjake.potatogame.Levels.GameMenu;
import org.gingerjake.potatogame.Levels.HeartGauntlet.HeartCurve1;
import org.gingerjake.potatogame.Levels.HeartGauntlet.HeartEnd;
import org.gingerjake.potatogame.Levels.HeartGauntlet.HeartEntrance;
import org.gingerjake.potatogame.Levels.HeartGauntlet.HeartFork;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Left.HeartCurve2;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Left.HeartCurveS;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Left.HeartHorizontal1;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Up.HeartCurve3;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Up.HeartHorizontal2;
import org.gingerjake.potatogame.Levels.HeartGauntlet.Up.HeartVertical;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedCurve2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedHorizontal2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Right.SpeedVertical2;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEnd;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedEntrance;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.SpeedFork;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedCurve1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedHorizontal1;
import org.gingerjake.potatogame.Levels.SpeedGauntlet.Up.SpeedVertical1;

import java.awt.*;
import java.util.Stack;

public class GameStateManager {
    public static final String version = "RMAKE-inDev";
    public static String currentLevel;
    public static Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
        states.push(new TestSpace());
    }

    public static void setState(GameState state) {
        states.push(state);
    }

    public static String getState() {
        return states.peek().getClass().getSimpleName();
    }

    public static void pause() {
        //TODO: Record X and Y of enemy and player, and then resume from that point in case levels set those values themselves.
        GameMenu.isPaused = true;
        currentLevel = getState();

        setState(new GameMenu());
    }

    public static void resume() {
        GameMenu.isPaused = false;
        PlayerController.enable();

        if (GameMenu.isGameOver) {
            setState(new TestSpace());
            GameMenu.isGameOver = false;
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
                case "DemoBoss" -> setState(new BossPrototypeFinal());
                case "HeartCurve2" -> setState(new HeartCurve2());
                case "HeartCurveS" -> setState(new HeartCurveS());
                case "HeartHorizontal2" -> setState(new HeartHorizontal2());
                case "HeartCurve3" -> setState(new HeartCurve3());
                case "HeartEnd" -> setState(new HeartEnd());
                case "HeartFork" -> setState(new HeartFork());
                case "HeartEntrance" -> setState(new HeartEntrance());
                case "HeartCurve1" -> setState(new HeartCurve1());
                case "HeartHorizontal1" -> setState(new HeartHorizontal1());
                case "HeartVertical" -> setState(new HeartVertical());

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