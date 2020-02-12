package com.franz.sud.java.game.cartridge.castlevania.helper;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.LifeSteal;
import com.franz.sud.java.game.platform.components.Health;

import java.util.Random;

public final class StatHelper {

    public static void lifesteal(int damage, LifeSteal lifeSteal, Health health) {
        lifeSteal.setDamage(damage);
        lifeSteal.setHealth(health);
    }

    public static boolean statEffectSuccessful(int statValue) {
        Random rand = new Random();
        int chance = rand.nextInt(100);
        return (chance <= statValue && chance > 0);
    }
//
//    public static void useItem(, GameStats unitStats) {
//
//    }
}
