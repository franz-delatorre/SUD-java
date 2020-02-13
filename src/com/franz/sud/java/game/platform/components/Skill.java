package com.franz.sud.java.game.platform.components;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;

public abstract class Skill {
    protected String name;
    private int cooldown;

    public String getName() {
        return name;
    }

    public abstract void skillEffect(GameUnit user, GameUnit victim);

    public int getCooldown() {
        return cooldown;
    }

    public void decrementCooldown() {
        if (cooldown > 0)
            cooldown--;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}
