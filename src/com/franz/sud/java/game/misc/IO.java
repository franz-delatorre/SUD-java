package com.franz.sud.java.game.misc;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;

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
            for (String string : option.keySet()) {
                if (opt == string) {
                    o = true;
                    ans = opt;
                }
            }
        } while (!o);
        return ans;
    }

    public static void printFightersHealth(GameUnit hero, GameUnit enemy) {
        System.out.println(hero.getName() + " \t\t\t\t\t"+ enemy.getName());
        System.out.print(hero.getCurrentHealth() + "/" + hero.getMaxHealth());
        System.out.println("\t\t\t\t\t" + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
    }

    public static void showConsumables(ArrayList<ConsumableItem> items) {
        int index = 1;
        for (ConsumableItem item : items) {
            System.out.println(index++ + ". " +item.getName());
        }
    }

    public static void showEquippables(ArrayList<EquippableItem> items) {
        int index = 1;
        for (EquippableItem item : items) {
            System.out.println(index++ + ". " + item.getName());
        }
    }
}
