package com.franz.sud.java.game.cartridge.castlevania.elements.item;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.platform.components.Item;

public abstract class AttributedItem extends Item {
    private int health;
    protected GameStats itemStats;
    private int damage;
    private boolean isPickedUp;
    
    abstract static class Builder<T extends Builder<T>> {
        private int health;
        private GameStats itemStats;
        private int damage;
        private String name;
        
        public Builder(String name) {
            health = 0;
            itemStats = new GameStats();
            damage = 0;
            this.name = name;
        }
        
        public T name(String name) {
            this.name = name;
            return self();
        }
        
        public T health(int healthValue) {
            health = healthValue;
            return self();
        }
        
        public T damage(int damage) {
            this.damage = damage;
            return self();
        }
        
        public T evasion(int evasionValue) {
            itemStats.increaseStat(StatType.EVASION, evasionValue);
            return self();
        }

        public T lifesteal(int lifestealValue) {
            itemStats.increaseStat(StatType.LIFESTEAL, lifestealValue);
            return self();
        }

        public T criticalChance(int critChanceValue) {
            itemStats.increaseStat(StatType.CRITICAL, critChanceValue);
            return self();
        }

        abstract AttributedItem build();

        protected abstract T self();
    }
    
    protected AttributedItem(Builder<?> builder) {
        super(builder.name);
        itemStats = builder.itemStats;
        damage = builder.damage;
        health = builder.health;
        isPickedUp = false;
    }

    public void pickedUp() {
        isPickedUp = true;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public GameStats getItemStats() {
        return itemStats;
    }
}
