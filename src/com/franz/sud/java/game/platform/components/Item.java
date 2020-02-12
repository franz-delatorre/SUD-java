package com.franz.sud.java.game.platform.components;

public abstract class Item {
    private String name;
    private int count;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void incrementCount() {
        count++;
    }

    public void decrementCount() {
        count--;
    }

    public int getCount() {
        return count;
    }

    public abstract void useItem();
}
