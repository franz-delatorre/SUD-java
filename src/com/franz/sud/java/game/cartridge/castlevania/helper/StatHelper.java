package com.franz.sud.java.game.cartridge.castlevania.helper;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;

import java.util.Random;

public final class StatHelper {
    public static boolean statEffectSuccessful(int statValue) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        return (chance <= statValue && chance > 0);
    }

    public static void increaseStats(GameStats primary, GameStats secondary) {
        for (StatType statType : StatType.values()) {
            int statValue = secondary.getStat(statType).getStatValue();
            primary.increaseStat(statType, statValue);
        }
    }

    public static void decreaseStats(GameStats primary, GameStats secondary) {
        for (StatType statType : StatType.values()) {
            int statValue = secondary.getStat(statType).getStatValue();
            primary.decreaseStat(statType, statValue);
        }
    }
}
