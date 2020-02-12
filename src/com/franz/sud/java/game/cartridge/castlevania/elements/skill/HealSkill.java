package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.platform.components.Skill;
import com.franz.sud.java.game.platform.components.Unit;

public class HealSkill extends Skill {
    private int healValue;

    public HealSkill(String name, int healValue) {
        this.healValue = healValue;
        this.name = name;
    }

    /**
     * Replenishes the user's current health by this heal value. If the
     * replenished heal is greater than the max health the current health
     * is set to the value of the max health. Thus limit the healing not
     * greater than max health.
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(Unit user, Unit victim) {
        user.heal(healValue);
    }

}
