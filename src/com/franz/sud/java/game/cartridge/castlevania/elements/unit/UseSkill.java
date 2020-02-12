package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.platform.components.Unit;

public interface UseSkill {
    void useSkill(GameUnit victim);
    boolean skillOnCooldown();
}
