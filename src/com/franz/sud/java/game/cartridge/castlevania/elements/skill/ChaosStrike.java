package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;

public class ChaosStrike extends Skill {
    public ChaosStrike() {
        name = "Chaos Strike";
    }

    /**
     * Deals 4 times the maximum damage of the user.
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(GameUnit user, GameUnit victim) {
        int damage = user.getDamage() * 4;
        victim.takeDamage(damage);
        IO.printDamage(damage);
    }
}
