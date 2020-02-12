package com.franz.sud.java.game.platform.components;

public abstract class Stats {
    private String name;
    protected int statValue;

    public Stats(int statValue) {
        this.statValue = statValue;
    }

    public String getName() {
        return name;
    }

    public void increaseStat(int statValue) {
        this.statValue = (this.statValue + statValue > 100) ? 99 : this.statValue + statValue;
    }

    public void decreaseStat(int statValue) {
        this.statValue = (this.statValue - statValue < 0) ? 0 : this.statValue - statValue;
    }

    public int getStatValue() {
        return statValue;
    }

    public void setStatValue(int statValue) {
        this.statValue = statValue;
    }

    protected abstract void statEffect();
}
