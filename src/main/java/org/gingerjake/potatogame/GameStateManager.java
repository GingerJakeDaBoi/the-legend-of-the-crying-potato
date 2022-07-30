package org.gingerjake.potatogame;

import java.awt.*;
import java.util.Stack;

public class GameStateManager {
    public Stack<GameState> states;

    public GameStateManager() {
        states = new Stack<>();
        states.push(new StartScreen(this));
    }

    public void tick() {
        states.peek().tick();
    }

    public void draw(Graphics g) {
        states.peek().draw(g);
    }
}