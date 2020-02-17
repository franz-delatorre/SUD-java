package com.franz.sud.java.game.platform.components;

public class Health {
    private int maxHealth;
    private int currentHealth;

    public Health(int health) {
        maxHealth = health;
        currentHealth = health;
    }

    public void decreaseCurrentHealth(int healthValue) {
        currentHealth = (currentHealth - healthValue < 0) ? 0 : currentHealth - healthValue;
    }

    public void increaseCurrenthealth(int healthvalue) {
        currentHealth = Math.min(currentHealth + healthvalue, maxHealth);
    }

    public void increaseMaxHealth(int healthValue) {
        maxHealth += healthValue;
    }

    public void decreaseMaxHealth(int healthValue) {
        maxHealth -= healthValue;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

}
