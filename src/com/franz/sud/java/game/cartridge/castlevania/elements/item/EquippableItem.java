package com.franz.sud.java.game.cartridge.castlevania.elements.item;

public class EquippableItem extends AttributedItem {
    private boolean isEquipped;
    private EquipmentType equipmentType;
    private boolean isPickedUp;

    // Builder Class
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

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
}
