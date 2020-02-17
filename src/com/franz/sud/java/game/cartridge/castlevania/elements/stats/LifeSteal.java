package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Health;
import com.franz.sud.java.game.platform.components.Stats;

public class LifeSteal extends Stats {
    private int lifesteal;
    private int damage;
    private Health health;

    public LifeSteal(int statValue) {
        super(statValue);
        name = "Lifesteal";
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    /**
     * Increases the current health of the user based on the percentage of the damage.
     */
    @Override
    public void statEffect() {
        lifesteal = (int) (damage * (statValue / 100.0f));
        health.increaseCurrenthealth(lifesteal);

        int currHealth = health.getCurrentHealth();
        int maxHealth = health.getMaxHealth();
        if (lifesteal + currHealth > maxHealth) IO.printLifesteal(maxHealth - currHealth);
        else IO.printLifesteal(lifesteal);
    }
}
