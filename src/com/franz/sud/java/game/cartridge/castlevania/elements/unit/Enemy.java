package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

public class Enemy extends GameUnit {

    public static class Builder extends GameUnit.Builder<Builder> {
        public Builder(String name) {
            super(name);
        }

        @Override
        public Enemy build() {
            return new Enemy(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    protected Enemy(GameUnit.Builder builder) {
        super(builder);
    }
}
