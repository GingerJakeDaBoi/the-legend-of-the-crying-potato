package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Levels.Debug.TestSpace;

import java.awt.*;
import java.util.Stack;

public class GameStateManager extends KeyListener {
    public static final String version = "RMAKE-inDev";
    static String currentLevel;
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

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}