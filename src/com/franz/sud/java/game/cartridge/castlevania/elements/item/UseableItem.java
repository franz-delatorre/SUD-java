package com.franz.sud.java.game.cartridge.castlevania.elements.item;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.platform.components.Item;

public abstract class UseableItem extends Item {
    private int damage;
    private GameStats stats;
    private GameStats unitStats;

    public UseableItem(String name, boolean used, int damage, GameStats stats) {
        super(name);
        this.damage = damage;
        this.stats = stats;
    }

    @Override
    public void useItem() {
    }

    public void setUnitStats(GameStats unitStats) {
        this.unitStats = unitStats;
    }

    public GameStats getUnitStats() {
        return unitStats;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public GameStats getStats() {
        return stats;
    }

    public void setStats(GameStats stats) {
        this.stats = stats;
    }
}
