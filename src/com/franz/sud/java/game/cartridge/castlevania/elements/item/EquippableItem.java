package com.franz.sud.java.game.cartridge.castlevania.elements.item;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;

public class EquippableItem extends AttributedItem {
    private boolean isEquipped;
    private EquipmentType equipmentType;
    private boolean isPickedUp;

    public static class Builder extends AttributedItem.Builder<Builder> {
        private EquipmentType equipmentType;

        public Builder(String name) {
            super(name);
        }

        public Builder equipmentType(EquipmentType e) {
            equipmentType = e;
            return self();
        }

        @Override
        public EquippableItem build() {
            return new EquippableItem(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private EquippableItem(Builder builder) {
        super(builder);
        isEquipped = false;
        equipmentType = builder.equipmentType;
        isPickedUp = false;
    }

    @Override
    public void useItem(Hero hero) {
        StatHelper.increaseStats(hero.getUnitStats(), itemStats);
        isEquipped = true;
    }

    public void unequip(Hero hero) {
        StatHelper.decreaseStats(hero.getUnitStats(), itemStats);
        isEquipped = false;
    }

    public void setPickedUp() {
        isPickedUp = true;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
}
