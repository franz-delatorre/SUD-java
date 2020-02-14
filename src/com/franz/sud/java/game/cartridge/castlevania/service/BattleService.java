package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.skill.StatBoostSkill;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.*;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Skill;

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

    private void useSkill(UseSkill user, GameUnit victim) {

        user.useSkill(victim);
        user.setCooldown(4);
    }

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

        int lsRate = attacker.getStat(StatType.LIFESTEAL).getStatValue();
        int lifesteal = (int) (damage * (lsRate / 100.0f));

        attacker.heal(lifesteal);
        IO.printLifesteal(lifesteal);
    }

    private boolean fightersStillAlive() {
        return hero.isAlive() && enemy.isAlive();
    }

    private void setCooldown() {
        hero.getSkill().setCooldown(3);
        if (enemy instanceof SkilledEnemy){
            ((SkilledEnemy) enemy).getSkill().setCooldown(3);
        }
    }

    private GameUnit winner() {
        IO.printWinner(hero, enemy);
        if (hero.isAlive()) {
            return hero;
        }
        return enemy;
    }

    private void decrementCooldown() {
        Skill heroSkill = hero.getSkill();
        heroSkill.decrementCooldown();
        if (enemy instanceof SkilledEnemy) {
            Skill enemySkill = ((SkilledEnemy) enemy).getSkill();
            enemySkill.decrementCooldown();
        }
    }

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
