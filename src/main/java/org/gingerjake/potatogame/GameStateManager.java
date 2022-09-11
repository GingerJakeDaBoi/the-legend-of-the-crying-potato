package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Actors.Enemies.Enemy;
import org.gingerjake.potatogame.Actors.Player.PlayerController;
import org.gingerjake.potatogame.Levels.Menus.PauseMenu;
import org.gingerjake.potatogame.Levels.Menus.StartScreen;
import org.gingerjake.potatogame.Levels.TestSpace;

import java.awt.*;
import java.util.Stack;

public class GameStateManager extends KeyListener {
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
        PauseMenu.paused = true;
        currentLevel = getState();

        setState(new PauseMenu());
        PlayerController.disable();
        Enemy.disable();

    }

    public static void resume() {
        PauseMenu.paused = false;
        PlayerController.enable();

        if (currentLevel.equals("StartScreen")) {
            setState(new StartScreen());
        } else {
            setState(new TestSpace());
        }
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}