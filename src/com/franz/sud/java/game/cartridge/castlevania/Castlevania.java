package com.franz.sud.java.game.cartridge.castlevania;

import com.franz.sud.java.game.cartridge.Cartridge;

public class Castlevania implements Cartridge {
    private boolean isFinished;
    private boolean gameOver;

    public Castlevania() {
        isFinished = false;
        gameOver = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public String getGameName() {
        return null;
    }
}
