package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.platform.components.Health;
import com.franz.sud.java.game.platform.components.Stats;

public class LifeSteal extends Stats {
    private int lifesteal;
    private int damage;
    private Health health;

    public LifeSteal(int statValue) {
        super(statValue);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    @Override
    protected void statEffect() {
        lifesteal = (int) (damage * (statValue / 100.0f));
        health.increaseMaxHealth(lifesteal);
    }
}
