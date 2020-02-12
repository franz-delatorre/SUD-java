package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.platform.components.Stats;

import java.util.HashMap;

public class GameStats {
    private HashMap<StatType, Stats> unitStats;

    public GameStats() {
        for (StatType statType : StatType.values()) {
            unitStats.put(statType, statType.getNewStatInstance());
        }
    }

    public Stats getStat(StatType statType) {
        return unitStats.get(statType);
    }

    public void increaseStat(StatType statType, int statValue) {
        unitStats.get(statType).increaseStat(statValue);
    }

    public void decreaseStat(StatType statType, int statValue) {
        unitStats.get(statType).decreaseStat(statValue);
    }
}
