package com.franz.sud.java.game.misc;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.GameStats;
import com.franz.sud.java.game.cartridge.castlevania.elements.stats.StatType;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.SkilledEnemy;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.UseSkill;
import com.franz.sud.java.game.platform.components.Item;
import com.franz.sud.java.game.platform.components.Skill;
import com.franz.sud.java.game.platform.components.Stats;
import com.franz.sud.java.game.platform.components.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.franz.sud.java.game.misc.Sleep.sleep;
import static com.franz.sud.java.game.misc.TextColor.ANSI_RESET;
import static com.franz.sud.java.game.misc.TextColor.ANSI_PURPLE;
import static com.franz.sud.java.game.misc.TextColor.ANSI_GREEN;
import static com.franz.sud.java.game.misc.TextColor.ANSI_RED;
import static com.franz.sud.java.game.misc.TextColor.ANSI_CYAN;
import static com.franz.sud.java.game.misc.TextColor.ANSI_BLUE;
import static com.franz.sud.java.game.misc.TextColor.ANSI_BLACK;

public final class IO {

    public static String userInput(HashMap<String, String> option) {
        Scanner sc = new Scanner(System.in);
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
        sleep(1);
        System.out.println();
        System.out.println(hero.getName() + " \t\t\t\t"+ enemy.getName());
        System.out.print(hero.getCurrentHealth() + "/" + hero.getMaxHealth());
        System.out.println("\t\t\t\t\t" + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
    }

    public static void printAttack(Unit unit) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_RESET + unit.getName() + " used normal attack." + ANSI_BLACK);
    }

    public static void printCrit() {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_PURPLE + "Critical HIT" + ANSI_BLACK);
    }

    public static void printEvade(Unit unit) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_PURPLE + unit.getName() + " evaded the attack" + ANSI_BLACK);
    }

    public static void printLifesteal(int ls) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_PURPLE + ls + " lifesteal" + ANSI_BLACK);
    }

    public static void printDamage(int damage) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_RED + damage + " damage" + ANSI_BLACK);
    }

    public static void printStatBoost(GameStats stats) {
        System.out.println();
        for (StatType statType : StatType.values()) {
            Stats s = stats.getStat(statType);
            if (s.getStatValue() > 0) {
                sleep(1);
                System.out.println(ANSI_BLUE + "+ " + s.getStatValue() + statType.toString() + ANSI_BLACK);
            }
        }
    }

    public static void printWinner(Unit hero, Unit enemy) {
        System.out.println();
        sleep(1);
        if (hero.getHealth().getCurrentHealth() > 0) {
            System.out.println(ANSI_GREEN + "You have slain " + enemy.getName() + ANSI_BLACK);
            return;
        }

        System.out.println(ANSI_RED + "You have been slain by " + enemy.getName() + ANSI_BLACK);
    }

    public static void printHeal(int heal) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_GREEN + "+ " + heal + " health" + ANSI_BLACK);
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
        System.out.println();
        sleep(1);
        if (items.get(0) instanceof ConsumableItem) System.out.println("Name\t\t\tAmount:");
        for (Item item : items) {
            if (item instanceof ConsumableItem) {
                int count = ((ConsumableItem) item).getCount();
                System.out.println(index++ + ". " + item.getName() + "\t\t" + count);
            } else {
                System.out.println(index++ + ". " + item.getName());
            }

        }
    }

    public static int getItemIndex(int index) {
        System.out.println();
        sleep(1);
        System.out.println("Please select an item number: ");
        Scanner s = new Scanner(System.in);

        while (!s.hasNextInt());
        int opt = s.nextInt();
        if (opt < 1 && opt > index) {
            System.out.println();
            sleep(1);
            System.out.println(ANSI_RED + "Index is out of bounds." + ANSI_BLACK);
            getItemIndex(index);
        }
        return opt;
    }

    public static void showItemAttributes(AttributedItem item) {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_CYAN + item.getName() + ANSI_BLACK);
        printItemAttributes(item);
    }

    public static void printItemAttributes(AttributedItem item) {
        sleep(1);
        if (item.getDamage() > 0) {
            sleep(1);
            System.out.println(ANSI_GREEN + "+ " + item.getDamage() + " Damage" + ANSI_BLACK);
        }
        if (item.getHealth() > 0) {
            sleep(1);
            System.out.println(ANSI_GREEN + "+ " + item.getHealth() + " Health" + ANSI_BLACK);
        }

        GameStats stats = item.getItemStats();
        for (StatType statType : StatType.values()) {
            Stats s = stats.getStat(statType);
            if (s.getStatValue() > 0) {
                sleep(1);
                System.out.println(ANSI_GREEN + "+ " + s.getStatValue() + " " + s.getName() + ANSI_BLACK);
            }
        }
    }

    public static String pickupItem() {
        boolean exitLoop = false;
        String s = null;

        System.out.println("You found an item, pick it up?");
        do {
        System.out.println();
        sleep(1);
        System.out.println("[Y] Yes");
        System.out.println("[N] No");

        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextLine());
        s = sc.nextLine().toLowerCase();
            if (s.equals("y") || s.equals("n")) exitLoop = true;
            System.out.println(ANSI_RED + "Invalid input" + ANSI_BLACK);
        } while (!exitLoop);
        return s;
    }

    public static void showCharacter(Hero hero) {
        System.out.println();
        sleep(1);
        System.out.println(hero.getName());
        System.out.println(ANSI_RESET + "Skill:" + hero.getSkill().getName());
        System.out.println("Health: " + hero.getMaxHealth());

        int minDamage = hero.getMinDamage();
        System.out.println("Damage: " + minDamage + "-" + hero.getDamage() + ANSI_BLACK);

        for (StatType statType : StatType.values()) {
            System.out.println(ANSI_CYAN + statType.toString() + ": " + hero.getStat(statType).getStatValue() + ANSI_BLACK);
        }
    }

    public static void itemAlreadyEquipped() {
        System.out.println();
        sleep(1);
        System.out.println(ANSI_RED + "Item is already equipped!" + ANSI_BLACK);
    }

    public static void printNarrative(String[] strings) {
        System.out.println();
        for (String string : strings) {
            sleep(2);
            System.out.println(ANSI_BLUE + string + ANSI_BLACK);
        }
    }
}
