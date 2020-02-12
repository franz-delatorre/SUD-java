package com.franz.sud.java.game.cartridge.castlevania.elements.item;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;

public class ConsumableItem  extends AttributedItem{
    private boolean isConsumed;
    private int tier;

    public static class Builder extends AttributedItem.Builder<Builder> {
        private int tier;

        public Builder(String name) {
            super(name);
        }

        public Builder tier(int tier) {
            this.tier = tier;
            return self();
        }

        @Override
        public ConsumableItem build() {
            return new ConsumableItem(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public int getTier() {
        return tier;
    }

    private ConsumableItem(Builder builder) {
        super(builder);
        this.tier = builder.tier;
        isConsumed = false;
    }

    @Override
    void useItem(Hero hero) {
        StatHelper.increaseStats(hero.getUnitStats(), itemStats);
        isConsumed = true;
    }

    public boolean isConsumed() {
        return isConsumed;
    }
}
