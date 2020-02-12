package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.*;
import com.franz.sud.java.game.cartridge.castlevania.helper.BattleHelper;
import com.franz.sud.java.game.misc.IO;

import java.util.HashMap;

public class BattleService {
    private static HashMap<String, String> input = new HashMap<>();
    private Hero hero;
    private Enemy enemy;

    public BattleService(Hero hero) {
        this.hero = hero;
    }

    public int simulateBattle(Enemy battleEnemy) {
        this.enemy = battleEnemy;
        hero.currentHealthToMax();
        enemy.currentHealthToMax();
        setCooldown();

        while (fightersStillAlive()) {
            getUserInput();
            IO.printFightersHealth(hero, enemy);
            if (fightersStillAlive()) break;
            enemyAI();
            IO.printFightersHealth(hero, enemy);
        }

        if (winner() == enemy){
            if (fightAgain()){
                simulateBattle(battleEnemy);
            }
            else {
                return 0;
            }
        }
        return 1;
    }

    private void enemyAI() {
        if (enemy instanceof UseSkill) {
            UseSkill ee = (UseSkill) enemy;
            if (ee.skillOnCooldown()) {
                useSkill(ee, hero);
                return;
            }
        }

        normalAttack(enemy, hero);
    }

    private void getUserInput() {
        input.clear();
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
    }

    private void normalAttack(GameUnit attacker, GameUnit victim) {
        if (victim.canEvade()) {
            return;
        }

        int minDamage = attacker.getMinDamage();
        int damage = (int) (Math.random() * ((attacker.getDamage() - minDamage) + 1)) + minDamage;

        if (attacker.canCrit()) {
            damage += damage;
        }

        victim.takeDamage(damage);

        if (!attacker.canLifesteal()) return;

        int lsRate = attacker.getStat(StatType.LIFESTEAL).getStatValue();
        int lifesteal = BattleHelper.totalLifesteal(damage, lsRate);

        attacker.heal(lifesteal);
    }

    private boolean fightersStillAlive() {
        return hero.isAlive() && enemy.isAlive();
    }

    private void setCooldown() {
        hero.getSkill().setCooldown(3);
        if (enemy instanceof SkilledEnemy)
            ((SkilledEnemy) enemy).getSkill().setCooldown(3);
    }

    private GameUnit winner() {
        if (hero.isAlive()) return hero;
        return enemy;
    }

    private boolean fightAgain() {
        input.clear();
        input.put("y", "Yes");
        input.put("n", "No");

        boolean fightAgain = true;
        switch (IO.userInput(input)) {
            case "y":
                fightAgain = true;
            case "n":
                fightAgain = false;
        }
        return fightAgain;
    }
}
