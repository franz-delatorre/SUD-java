package com.franz.sud.java.game.platform.components;

public abstract class Unit {
    private String name;
    protected int damage;
    protected Health health;

    public Unit(String name, int damage, Health health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
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

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public Health getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health.getCurrentHealth() > 0;
    }
}
