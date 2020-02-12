package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

import com.franz.sud.java.game.platform.components.Room;
import com.franz.sud.java.game.platform.components.Skill;
import com.franz.sud.java.game.platform.components.Unit;

public class Hero extends GameUnit implements UseSkill{
    private Skill skill;
    private Room previousRoom;

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

    private Hero(Builder builder) {
        super(builder);
        previousRoom = null;
        skill = builder.skill;
    }

    public boolean skillOnCooldown() {
        return skill.getCooldown() > 0;
    }

    public Skill getSkill() {
        return skill;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }
}
