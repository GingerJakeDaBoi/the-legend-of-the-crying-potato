package org.gingerjake.potatogame;

import org.gingerjake.potatogame.Screens.StartScreen;

import java.awt.*;
import java.util.Stack;

public class GameStateManager extends KeyListener {
    public static Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
        states.push(new StartScreen());
    }

    public static void setState(GameState state) {
        states.push(state);
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}