package org.gingerjake.potatogame;


import java.awt.*;

public abstract class GameState {
    public abstract void init();

    public static GameStateManager gsm;

    public abstract void draw(Graphics g);

    public abstract void tick();

    public GameState(GameStateManager gsm) {
        GameState.gsm = gsm;
        init();
    }

}