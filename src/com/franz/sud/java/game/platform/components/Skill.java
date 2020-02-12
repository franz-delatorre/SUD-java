package com.franz.sud.java.game.platform.components;

public abstract class Skill {
    public String name;

    public String getName() {
        return name;
    }

    abstract void skillEffect();
}
