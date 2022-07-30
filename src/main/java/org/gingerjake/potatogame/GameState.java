package org.gingerjake.potatogame;


import java.awt.*;

public abstract class GameState {
    public abstract void init();
    protected static GameStateManager gsm;
    public abstract void draw(Graphics g);
    public abstract void tick();

    public GameState(GameStateManager gsm){
        GameState.gsm = gsm;
        init();
    }

    public static void setState(GameState state){
        gsm.setState(state);
    }
}