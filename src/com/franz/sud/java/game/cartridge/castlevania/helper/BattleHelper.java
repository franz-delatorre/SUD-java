package com.franz.sud.java.game.cartridge.castlevania.helper;

public class BattleHelper {
    public static int totalLifesteal(int damage, int lifestealRate) {
        return (int) (damage * (lifestealRate / 100.0f));
    }
}
