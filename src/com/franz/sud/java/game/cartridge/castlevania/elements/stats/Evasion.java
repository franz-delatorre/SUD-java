package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;
import com.franz.sud.java.game.platform.components.Stats;

public class Evasion extends Stats {
    private boolean evaded;

    public Evasion(int statValue) {
        super(statValue);
    }

    public boolean hasEvaded() {
        statEffect();
        return evaded;
    }

    @Override
    protected void statEffect() {
        evaded = StatHelper.statEffectSuccessful(statValue);
    }
}
