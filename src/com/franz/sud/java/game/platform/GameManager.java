package com.franz.sud.java.game.platform;

import com.franz.sud.java.game.cartridge.Cartridge;

public class GameManager {
    private Cartridge game;

    public void setGame(Cartridge game) {
        this.game = game;
    }

    public void startGame() {
        game.init();
        do {
            game.start();
        } while (!game.isFinished());

        if (game.gameOver()) {
            System.out.println("Game Over!!");
        } else {
            System.out.println("Congratulations! You completed " + game.getGameName());
        }
    }
}
