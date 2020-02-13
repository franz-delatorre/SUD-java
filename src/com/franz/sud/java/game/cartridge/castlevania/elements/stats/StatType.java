package com.franz.sud.java.game.cartridge.castlevania.elements.stats;

import com.franz.sud.java.game.platform.components.Stats;

public enum StatType {
    EVASION {
        @Override
        public String toString() {
            return "Evasion";
        }

        @Override
        public Stats getNewStatInstance() {
            return new Evasion(0);
        }
    },
    LIFESTEAL {
        @Override
        public String toString() {
            return "Lifesteal";
        }

        @Override
        public Stats getNewStatInstance() {
            return new LifeSteal(0);
        }
    },
    CRITICAL {
        @Override
        public String toString() {
            return "Critical";
        }

        @Override
        public Stats getNewStatInstance() {
            return new CriticalChance(0);
        }
    };

    public Stats getNewStatInstance() {
        return null;
    }
}
