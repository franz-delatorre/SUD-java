package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;

public class DamageSkill extends Skill {
    private int damage;

    public DamageSkill(String name, int damage) {
        this.damage = damage;
        this.name = name;
    }

    /**
     * Deals damage to the opponent. The damage dealt is based on
     * the damage set.
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(GameUnit user, GameUnit victim) {
        victim.takeDamage(damage);
        IO.printDamage(damage);
    }
}
