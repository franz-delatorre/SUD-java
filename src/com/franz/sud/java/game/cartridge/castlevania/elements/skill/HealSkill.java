package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;

public class HealSkill extends Skill {
    private int healValue;

    public HealSkill(String name, int healValue) {
        this.healValue = healValue;
        this.name = name;
    }

    /**
     * Replenishes the user's current health by the healValue set.
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(GameUnit user, GameUnit victim) {
        IO.printHeal(healValue);
        user.heal(healValue);
    }
}
