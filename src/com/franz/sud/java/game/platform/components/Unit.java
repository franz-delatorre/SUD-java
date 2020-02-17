package com.franz.sud.java.game.platform.components;

public abstract class Unit {
    protected String name;
    protected int damage;
    protected Health health;

    public Unit() {
    }

    public void takeDamage(int damage) {
        health.decreaseCurrentHealth(damage);
    }

    public void heal(int heal) {
        health.increaseCurrenthealth(heal);
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
