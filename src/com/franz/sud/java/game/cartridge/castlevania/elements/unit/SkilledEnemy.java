package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.platform.components.Item;
import com.franz.sud.java.game.platform.components.Skill;

public class SkilledEnemy extends Enemy implements UseSkill{
    private Skill skill;
    private Item item;
    private boolean itemDropped;

    public static class Builder extends GameUnit.Builder<Builder> {
        private Skill skill;
        private Item item;

        public Builder(String name) {
            super(name);
            skill = null;
            item = null;
        }

        public Builder item(Item item) {
            this.item = item;
            return self();
        }

        public Builder skill(Skill skill) {
            this.skill = skill;
            return self();
        }

        @Override
        public SkilledEnemy build() {
            return new SkilledEnemy(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private SkilledEnemy(Builder builder) {
        super(builder);
        itemDropped = false;
        skill = builder.skill;
    }

    @Override
    public String getSkillName() {
        return skill.getName();
    }

    @Override
    public void useSkill(GameUnit victim) {
        skill.skillEffect(this, victim);
    }

    @Override
    public void setCooldown(int cd) {
        skill.setCooldown(cd);
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public boolean skillOnCooldown() {
        return skill.getCooldown() > 0;
    }

    public Skill getSkill() {
        return skill;
    }
}
