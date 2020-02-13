package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.CriticalChance;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.Evasion;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.platform.components.Health;
import com.franz.sud.java.game.platform.components.Room;
import com.franz.sud.java.game.platform.components.Stats;
import com.franz.sud.java.game.platform.components.Unit;

public class GameUnit extends Unit {
    protected GameStats unitStats;
    private Room currentLocation;

    abstract static class Builder<T extends Builder<T>> {
        private String name;
        private int damage;
        private Health health;
        private GameStats unitStats;
        private Room currentLocation;

        public Builder(String name) {
            this.name = name;
            damage = 0;
            health = new Health(0);
            unitStats = new GameStats();
            currentLocation = null;
        }

        public T damage(int damage) {
            this.damage = damage;
            return self();
        }

        public T health(int health) {
            this.health = new Health(health);
            return self();
        }

        public T evasion(int evasion) {
            unitStats.increaseStat(StatType.EVASION, evasion);
            return self();
        }

        public T lifesteal(int lifesteal) {
            unitStats.increaseStat(StatType.LIFESTEAL, lifesteal);
            return self();
        }

        public T criticalChance(int critChance) {
            unitStats.increaseStat(StatType.CRITICAL, critChance);
            return self();
        }

        public T location(Room room) {
            currentLocation = room;
            return self();
        }

        abstract GameUnit build();

        protected abstract T self();

    }

    protected GameUnit(Builder<?> builder) {
        super();
        name = builder.name;
        health = builder.health;
        damage = builder.damage;
        unitStats = builder.unitStats;
    }

    public boolean canEvade() {
        Evasion ev = (Evasion) unitStats.getStat(StatType.EVASION);
        return ev.hasEvaded();
    }

    public boolean canCrit() {
        CriticalChance cc = (CriticalChance) unitStats.getStat(StatType.CRITICAL);
        return cc.canCrit();
    }

    public boolean canLifesteal() {
        Stats ls = unitStats.getStat(StatType.LIFESTEAL);
        return ls.getStatValue() > 0;
    }

    public void setCurrentLocation(Room currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Room getCurrentLocation() {
        return currentLocation;
    }

    public GameStats getUnitStats() {
        return unitStats;
    }

    public Stats getStat(StatType statType) {
        return unitStats.getStat(statType);
    }

    public int getCurrentHealth() {
        return health.getCurrentHealth();
    }

    public int getMaxHealth() {
        return health.getMaxHealth();
    }

    public int getMinDamage() {
        return (int) (damage - (damage * .1));
    }

    public void currentHealthToMax() {
        health.increaseCurrenthealth(999999);
    }
}
