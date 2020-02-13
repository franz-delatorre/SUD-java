package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquipmentType;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;
import com.franz.sud.java.game.platform.components.Skill;

import java.util.HashMap;

public class Hero extends GameUnit implements UseSkill{
    private Skill skill;
    private HashMap<EquipmentType, EquippableItem> equippedItem = new HashMap<>();

    public static class Builder extends GameUnit.Builder<Builder> {
        private Skill skill;

        public Builder(String name) {
            super(name);
            skill = null;
        }

        public Builder skill(Skill skill) {
            this.skill = skill;
            return self();
        }

        @Override
        public Hero build() {
            return new Hero(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
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
    public String getSkillName() {
        return skill.getName();
    }

    private Hero(Builder builder) {
        super(builder);
        skill = builder.skill;
    }

    public boolean skillOnCooldown() {
        return skill.getCooldown() > 0;
    }

    public Skill getSkill() {
        return skill;
    }

    public void equipItem(EquippableItem item) {
        EquipmentType et = item.getEquipmentType();
        if (equippedItem.containsKey(et)) {
            StatHelper.decreaseStats(unitStats, item.getItemStats());
        }
        StatHelper.increaseStats(unitStats, item.getItemStats());
    }

    public void consumeItem(ConsumableItem item) {
        StatHelper.increaseStats(unitStats, item.getItemStats());
    }
}
