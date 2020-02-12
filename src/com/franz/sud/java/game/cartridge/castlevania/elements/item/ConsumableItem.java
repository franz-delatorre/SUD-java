package com.franz.sud.java.game.cartridge.castlevania.elements.item;

public class ConsumableItem extends UseableItem {
    @Override
    public void useItem() {
        decrementCount();
    }
}
