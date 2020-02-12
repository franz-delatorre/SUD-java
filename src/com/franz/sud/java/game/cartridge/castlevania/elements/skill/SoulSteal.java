package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.platform.components.Health;
import com.franz.sud.java.game.platform.components.Skill;

public class SoulSteal extends Skill {
    public SoulSteal(){
        name = "Soul Steal";
    }

    /**
     * Deals 2 times the maximum damage of the user and heals the user
     * for the same amount of the damage output.
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(GameUnit user, GameUnit victim) {
        int damage = user.getDamage() * 2;
        victim.takeDamage(damage);
        user.heal(damage);
        user.getDamage();
    }
}
