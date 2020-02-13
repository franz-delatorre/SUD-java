package com.franz.sud.java.game.cartridge.castlevania.elements.unit;

public interface UseSkill {
    void useSkill(GameUnit victim);
    boolean skillOnCooldown();
    void setCooldown(int cd);
    String getSkillName();
    String getUserName();
}
