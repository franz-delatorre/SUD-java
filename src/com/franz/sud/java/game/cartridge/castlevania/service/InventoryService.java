package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquipmentType;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.misc.IO;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryService {
    private static final HashMap<String, String> input = new HashMap<>();

    private ArrayList<ConsumableItem> consumables = new ArrayList<>();
    private ArrayList<EquippableItem> equippables = new ArrayList<>();
    private EnumMap<EquipmentType, EquippableItem> equippedItem = new EnumMap<>(EquipmentType.class);
    private Hero hero;

    public InventoryService() {
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * Opens the Menu for the Inventory
     */
    public void openInventoryMenu() {
        input.clear();
        input.put("e", "Exit Inventory Menu");
        if (!consumables.isEmpty()) input.put("c", "Show Consumable Items");
        if (!equippables.isEmpty()) input.put("i", "Show Equippable Items");

        switch (IO.userInput(input)){
            case "c":
                IO.showInventory(consumables);
                attributedItemsMenu(consumables);
                openInventoryMenu();
                break;
            case "i":
                IO.showInventory(equippables);
                attributedItemsMenu(equippables);
                openInventoryMenu();
                break;
            case "e":
                return;
        }
    }

    /**
     * Adds an item that can be used by the player.
     * @param item
     */
    public void addItemToInventory(AttributedItem item) {
        if (item instanceof ConsumableItem) {
            int index = consumables.indexOf(item);
            if (index > -1) {
                ConsumableItem cons = consumables.get(index);
                cons.incrementCount();
            } else {
                consumables.add((ConsumableItem) item);
                ((ConsumableItem) item).incrementCount();
            }
        } else if (item instanceof EquippableItem) {
            equippables.add((EquippableItem) item);
        }
    }

    /**
     * Shows the current items in posession of the player. If the list passed in the parameter
     * is == to equippables, it will show all equippable items. Likewise, shows the consumable
     * items if the list is == to equippables.
     * @param items
     */
    private  void attributedItemsMenu(ArrayList<? extends AttributedItem> items) {
        input.clear();
        input.put("i", "Inspect Item");
        if (items == equippables) {
            input.put("u", "Equip Item");
        } else {
            input.put("u", "Use Item");
        }
        input.put("e", "Exit Item Menu");

        int index;

        Scanner sc = new Scanner(System.in);
        String o = IO.userInput(input);
        System.out.println(o);
        switch (o) {
            case "i":
                index = IO.getItemIndex(items.size());
                inspectItem(items, index);
                break;
            case "u":
                index = IO.getItemIndex(items.size());
                useItem(items, index);
                break;
            case "e":
                return;
        }
    }

    /**
     * Inspects the index of the current list.
     * @param items
     * @param index
     */
    public void inspectItem(ArrayList<? extends AttributedItem> items, int index) {
        AttributedItem item = items.get(index - 1);
        IO.showItemAttributes(item);
    }

    /**
     * Checks the equivalent list of the given list, then uses the item of the
     * list's index.
     * @param items
     * @param index
     */
    public void useItem(ArrayList<? extends AttributedItem> items, int index) {

        if (items == equippables) {
            equipItem(index);
        }

        if (items == consumables) {
            consumeItem(index);
        }
    }

    /**
     * Equips the item of the of the current list.
     * @param index
     */
    public void equipItem(int index) {
        EquippableItem item = equippables.get(index - 1);
        EquipmentType et = item.getEquipmentType();

        if (equippedItem.get(et) == item) {
            IO.itemAlreadyEquipped();
            return;
        }

        hero.equipItem(item);
        equippedItem.put(et, item);

        IO.printItemAttributes(item);
    }

    /**
     * Consumes the item specified.
     * @param index
     */
    public void consumeItem(int index) {
        ConsumableItem item = consumables.get(index - 1);
        hero.consumeItem(item);
        if (item.getCount() < 1) consumables.remove(index -1);
    }

}
