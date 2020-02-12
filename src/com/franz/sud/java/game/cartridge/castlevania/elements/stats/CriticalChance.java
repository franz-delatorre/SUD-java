package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;
import com.franz.sud.java.game.platform.components.Stats;

public class CriticalChance extends Stats {
    private boolean canCrit;

    public CriticalChance(int statValue) {
        super(statValue);
    }

    public boolean canCrit() {
        statEffect();
        return canCrit;
    }

    @Override
    protected void statEffect() {
        canCrit = StatHelper.statEffectSuccessful(statValue);
    }
}
