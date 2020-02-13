package com.franz.sud.java.game.misc;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.SkilledEnemy;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.UseSkill;
import com.franz.sud.java.game.platform.components.Skill;
import com.franz.sud.java.game.platform.components.Stats;
import com.franz.sud.java.game.platform.components.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class IO {
    private static final Scanner sc = new Scanner(System.in);

    public static String userInput(HashMap<String, String> option) {
        String ans = null;
        boolean o = false;
        do {
            for (String string : option.keySet())
                System.out.println("[" + string.toUpperCase() + "] " + option.get(string));
            while (!sc.hasNextLine());
            String opt = sc.nextLine();
            if (option.containsKey(opt.toLowerCase()) || option.containsKey(opt.toUpperCase())) {
                ans = opt.toLowerCase();
                o = true;
            }
        } while (!o);
        return ans;
    }

    public static void printFightersHealth(GameUnit hero, GameUnit enemy) {
        System.out.println();
        System.out.println(hero.getName() + " \t\t\t\t"+ enemy.getName());
        System.out.print(hero.getCurrentHealth() + "/" + hero.getMaxHealth());
        System.out.println("\t\t\t\t\t" + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
    }

    public static void printAttack(Unit unit) {
        System.out.println(unit.getName() + " used normal attack.");
    }

    public static void printAttack(Unit unit, Skill skill) {
        System.out.println(unit.getName() + " used " + skill.getName());
    }

    public static void printCrit() {
        System.out.println("Critical HIT");
    }

    public static void printEvade(Unit unit) {
        System.out.println(unit.getName() + " evaded the attack");
    }

    public static void printLifesteal(int ls) {
        System.out.println(ls + " lifesteal");
    }

    public static void printDamage(int damage) {
        System.out.println(damage + " damage");
    }

    public static void printStatBoost(GameStats stats) {
        for (StatType statType : StatType.values()) {
            Stats s = stats.getStat(statType);
            if (s.getStatValue() > 0) {
                System.out.println("+ " + s.getStatValue() + statType.toString());
            }
        }
    }

    public static void printWinner(Unit hero, Unit enemy) {
        if (hero.getHealth().getCurrentHealth() > 0) {
            System.out.println("You have slain " + enemy.getName());
            return;
        }

        System.out.println("You have been slain by " + enemy.getName());
    }

    public static void printHeal(int heal) {
        System.out.println("+ " + heal + " health");
    }

    public static void printSkillCooldown(Skill skill, GameUnit enemy) {
        System.out.print("Cd: " + skill.getCooldown() + " \t\t\t\t\t");
        if (enemy instanceof UseSkill) {
            System.out.print("Cd: " + ((SkilledEnemy) enemy).getSkill().getCooldown());
        }
        System.out.println();
    }

    public static <T extends AttributedItem> void showInventory(ArrayList<T> items) {
        int index = 1;
        for (T item : items) {
            System.out.println(index++ + ". " + item.getName());
        }
    }

    public static int getItemIndex(int index) {
        System.out.println("Please select an item number: ");
        while (!sc.hasNextInt());
        int opt = sc.nextInt();
        if (opt < 1 && opt > index) {
            System.out.println("Index is out of bounds.");
            getItemIndex(index);
        }
        return opt;
    }

    public static void showItemAttributes(AttributedItem item) {
        System.out.println(item.getName());
        if (item.getDamage() > 0) System.out.println("+ " + item.getDamage() + " Damage");
        if (item.getHealth() > 0) System.out.println("+ " + item.getHealth() + " Health");

        GameStats stats = item.getItemStats();
        for (StatType statType : StatType.values()) {
            Stats s = stats.getStat(statType);
            if (s.getStatValue() > 0)
                System.out.println("+ " + s.getStatValue() + " " + s.getName());
        }
    }

    public static String pickupItem() {
        boolean exitLoop = false;
        String s = null;

        do {
        System.out.println();
        System.out.println("[Y] Yes");
        System.out.println("[N] No");

        while (!sc.hasNextLine());
        s = sc.nextLine().toLowerCase();
            if (s.equals("y") || s.equals("n")) exitLoop = true;
            System.out.println("Invalid input");
        } while (!exitLoop);
        return s;
    }
}
