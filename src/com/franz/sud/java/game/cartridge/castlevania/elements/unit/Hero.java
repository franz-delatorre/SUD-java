package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquipmentType;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.helper.StatHelper;
import com.franz.sud.java.game.misc.IO;
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

    /**
     *
     * @return true if the skill cooldown is > 0 and false if otherwise.
     */
    public boolean skillOnCooldown() {
        return skill.getCooldown() > 0;
    }

    public Skill getSkill() {
        return skill;
    }

    /**
     * Improves the unit's damage, health and stats based on the item's
     * attributes. Will also equip the item in the equippedItem variable.
     * If another existing item is already equipped before equipping the new item
     * The previous item's attributes is decreased from the unit and the new item
     * is increase to the unit's attributes.
     * @param item
     */
    public void equipItem(EquippableItem item) {
        EquipmentType et = item.getEquipmentType();

        if (equippedItem.containsKey(et)) {
            StatHelper.decreaseStats(unitStats, item.getItemStats());
            AttributedItem i = equippedItem.get(et);

            damage -= i.getDamage();
            health.decreaseMaxHealth(i.getHealth());
        }

        StatHelper.increaseStats(unitStats, item.getItemStats());
        damage += item.getDamage();
        health.increaseMaxHealth(item.getHealth());
    }

    /**
     * Improves the unit's damage, health, and stats based on the item's attributes.
     * Then decrements the items number by 1 upon usage. If the count of the item is 1
     * it will be removed from the list upon usage.
     * @param item
     */
    public void consumeItem(ConsumableItem item) {
        StatHelper.increaseStats(unitStats, item.getItemStats());
        damage += item.getDamage();
        health.increaseMaxHealth(item.getHealth());
        item.decrementCount();
        IO.showItemAttributes(item);
    }
}
