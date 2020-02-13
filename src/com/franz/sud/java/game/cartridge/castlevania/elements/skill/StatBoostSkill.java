package com.franz.sud.java.game.cartridge.castlevania.elements.skill;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;

public class StatBoostSkill extends Skill {
    private GameStats skillStats;
    private int duration;

    public static class Builder {
        private String name;
        private int duration;
        private GameStats skillStats = new GameStats();

        public Builder(String name){
            this.name = name;
        }

        public Builder evasion(int statValue){
            skillStats.increaseStat(StatType.EVASION, statValue);
            return this;
        }

        public Builder lifesteal(int statValue) {
            skillStats.increaseStat(StatType.LIFESTEAL, statValue);
            return this;
        }

        public Builder criticalChance(int statValue) {
            skillStats.increaseStat(StatType.CRITICAL, statValue);
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public StatBoostSkill build() {
            return new StatBoostSkill(this);
        }
    }

    private StatBoostSkill(Builder builder) {
        skillStats = builder.skillStats;
        duration = builder.duration;
        name = builder.name;
    }

    public int getDuration() {
        return duration;
    }

    public void decreaseDuration() {
        duration--;
    }

    /**
     * Buffs the user's stats for a limited turn
     * @param user
     * @param victim
     */
    @Override
    public void skillEffect(GameUnit user, GameUnit victim) {
        duration = 3;
        StatHelper.increaseStats(user.getUnitStats(), skillStats);
        IO.printStatBoost(skillStats);
    }

    /**
     * Debuffs the user's stats if the limited turn is over.
     * @param unit
     */
    public void skillAfterEffect(GameUnit unit) {
        StatHelper.decreaseStats(unit.getUnitStats(), this.skillStats);
    }
}
