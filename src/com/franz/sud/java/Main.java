package com.franz.sud.java;

import com.franz.sud.java.game.cartridge.castlevania.Castlevania;
import com.franz.sud.java.game.platform.GameManager;

public class Main {

    public static void main(String[] args) {
        Castlevania castlevania = new Castlevania();
        GameManager manager = new GameManager();

        manager.setGame(castlevania);
        manager.startGame();
    }
}
