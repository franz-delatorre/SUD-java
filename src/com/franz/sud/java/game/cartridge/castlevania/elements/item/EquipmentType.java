package com.franz.sud.java.game.cartridge.castlevania.elements.item;

public enum EquipmentType {
    WEAPON {
        @Override
        public String toString() {
            return "Weapon";
        }
    },
    ARMOR {
        @Override
        public String toString() {
            return "Armor";
        }
    },
    AMULET {
        @Override
        public String toString() {
            return "Amulet";
        }
    };
}
