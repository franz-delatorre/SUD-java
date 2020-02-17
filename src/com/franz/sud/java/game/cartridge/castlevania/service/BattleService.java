package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.skill.StatBoostSkill;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.LifeSteal;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.*;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;
import com.franz.sud.java.game.platform.components.Stats;

import java.util.HashMap;

public class BattleService {
    private static HashMap<String, String> input = new HashMap<>();
    private Hero hero;
    private Enemy enemy;

    public BattleService() {
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * Enables the unit to mimic a battle. Player and computer will take turns
     * in doing action during the battle. If one of the units currentHealth reaches to 0 the
     * battle will end.
     *
     * @return 1 if the player wins, and 0 if otherwise.
     */
    public int simulateBattle(Enemy battleEnemy) {
        this.enemy = battleEnemy;
        hero.currentHealthToMax();
        enemy.currentHealthToMax();
        setCooldown();

        int turn = 1;
        while (fightersStillAlive()) {
            System.out.println("=============================");
            IO.printFightersHealth(hero, enemy);
            IO.printSkillCooldown(hero.getSkill(), enemy);
            getUserInput();
            IO.printFightersHealth(hero, enemy);

            if (!fightersStillAlive()) break;
            System.out.println("\n" + enemy.getName() + "'s turn");
            enemyAI();
            IO.printFightersHealth(hero, enemy);

            System.out.println("\t \t End of Turn " + turn++);
            System.out.println("=============================");
            decrementCooldown();
            decrementBoost();
        }

        if (winner() == enemy){
            return 0;
        }
        return 1;
    }

    /**
     * Enables the enemy to do an attack to the player. SkilledEnemy instances will use
     * their skill if the cooldown is <= 0 and do a normal attack if the attack is still
     * on cooldown. If the enemy does not have a skill it will only do a normal attack.
     */
    private void enemyAI() {
        if (enemy instanceof UseSkill) {
            UseSkill ee = (UseSkill) enemy;
            if (!ee.skillOnCooldown()) {
                useSkill(ee, hero);
                return;
            }
        }

        normalAttack(enemy, hero);
    }

    /**
     * Gets the user's input during the battle.
     */
    private void getUserInput() {
        input.clear();
        System.out.println("Your turn.");
        input.put("a", "Normal Attack");

        if (!hero.skillOnCooldown()) {
            input.put("s", "Use Skill");
        }

        switch (IO.userInput(input)) {
            case "a":
                normalAttack(hero, enemy);
                break;
            case "s":
                useSkill(hero, enemy);
                break;
        }
    }

    /**
     * Invokes the skill of the user.
     * @param user
     * @param victim
     */
    private void useSkill(UseSkill user, GameUnit victim) {
        user.useSkill(victim);
        user.setCooldown(4);
    }

    /**
     * Basic attack of the game. If the victim's canEvade is true, it will not do any
     * damage. If the attacker canEvade is true it will do a double damage attack.
     * If the lifesteal of the attacker is > 0, the attacker will replenish its current
     * health based on the percentage of the damage dealth.
     * @param attacker
     * @param victim
     */
    private void normalAttack(GameUnit attacker, GameUnit victim) {
        IO.printAttack(attacker);

        if (victim.canEvade()) {
            IO.printEvade(victim);
            return;
        }

        int minDamage = attacker.getMinDamage();
        int damage = (int) (Math.random() * ((attacker.getDamage() - minDamage) + 1)) + minDamage;

        if (attacker.canCrit()) {
            IO.printCrit();
            damage += damage;
        }

        victim.takeDamage(damage);
        IO.printDamage(damage);

        if (!attacker.canLifesteal()) return;

        LifeSteal ls = (LifeSteal) attacker.getStat(StatType.LIFESTEAL);

        ls.setHealth(attacker.getHealth());
        ls.setDamage(damage);
        ls.statEffect();
    }

    /**
     * Checks if both fighter's current health is > 0
     * @return
     */
    private boolean fightersStillAlive() {
        return hero.isAlive() && enemy.isAlive();
    }

    /**
     * Sets the cooldown of both fighters to 3 before battle battle
     */
    private void setCooldown() {
        hero.getSkill().setCooldown(3);
        if (enemy instanceof SkilledEnemy){
            ((SkilledEnemy) enemy).getSkill().setCooldown(3);
        }
    }

    /**
     * Gets the winner
     * @return
     */
    private GameUnit winner() {
        IO.printWinner(hero, enemy);
        if (hero.isAlive()) {
            return hero;
        }
        return enemy;
    }

    /**
     * Decrements the cooldown of both fighters after each turn
     */
    private void decrementCooldown() {
        Skill heroSkill = hero.getSkill();
        heroSkill.decrementCooldown();
        if (enemy instanceof SkilledEnemy) {
            Skill enemySkill = ((SkilledEnemy) enemy).getSkill();
            enemySkill.decrementCooldown();
        }
    }

    /**
     * Decrements Boost skills duration. If duration is == 0 the
     * user will have an after effect and decrease its stats.
     */
    private void decrementBoost() {
        if (enemy instanceof SkilledEnemy) {
            Skill skill = ((SkilledEnemy) enemy).getSkill();
            if (skill instanceof StatBoostSkill) {
                StatBoostSkill s = (StatBoostSkill) skill;
                if (s.getDuration() == 0) {
                    s.skillAfterEffect(enemy);
                }
                s.decreaseDuration();
            }
        }
    }
}
