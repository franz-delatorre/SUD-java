package com.franz.sud.java.game.platform.components;

public abstract class Unit {
    private String name;
    private int damage;
    private Health health;
    private Room currentLocation;

    public abstract class Builder <T extends Builder<T>>{
        private String name;
        private int damage;
        private Stats stats;
        private Health health;
        private Room currentLocation;

        Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder stats(Stats stats) {
            this.stats = stats;
            return this;
        }

        public Builder health(int health) {
            this.health = new Health(health);
            return this;
        }

        public Builder currentLocation(Room room) {
            this.currentLocation = room;
            return this;
        }

        abstract void build();
    }

    protected Unit(Builder<?> builder) {
        name = builder.name;
        damage = builder.damage;
        health = builder.health;
        currentLocation = builder.currentLocation;
    }

    public void takeDamage(int damage) {
        health.decreaseCurrentHealth(damage);
    }

    public void heal(int heal) {
        health.increaseCurrenthealth(heal);
    }

    public void increaseMaxHealth(int hpVal) {
        health.increaseMaxHealth(hpVal);
    }
}
