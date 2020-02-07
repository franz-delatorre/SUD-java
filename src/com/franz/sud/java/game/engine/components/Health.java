package com.franz.sud.java.game.engine.components;

public class Health {
    private int maxHealth;
    private int currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void increaseMaxHealth(int healthValue) {
    }

    public void decreaseMaxHealth(int healthValue) {

    }

    public void decreaseCurrentHealth(int healthValue) {

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
}
