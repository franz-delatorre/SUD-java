package com.franz.sud.java.game.cartridge.castlevania.elements.item;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;

public class EquippableItem extends UseableItem {
    private boolean isEquipped;
    private ItemType itemType;

    public EquippableItem(String name, boolean used, int damage, GameStats stats) {
        super(name, used, damage, stats);
        isEquipped = false;
    }

    @Override
    public void useItem() {
        equip();
    }

    public void equip() {
        isEquipped = true;
    }

    public void unequip() {
        isEquipped = false;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
