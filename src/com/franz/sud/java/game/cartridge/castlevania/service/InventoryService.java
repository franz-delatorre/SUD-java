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

    public void OpenInventoryMenu() {
        boolean exitMenu = false;
        do {
            input.clear();
            if (!consumables.isEmpty()) input.put("c", "Show Consumable Items");
            if (!equippables.isEmpty()) input.put("i", "Show Equippable Items");
            input.put("e", "Exit Inventory Menu");

            switch (IO.userInput(input)){
                case "c":
                    IO.showInventory(consumables);
                    attributedItemsMenu(consumables);
                    break;
                case "i":
                    IO.showInventory(equippables);
                    attributedItemsMenu(equippables);
                    break;
                case "e":
                    exitMenu = true;
            }
        } while (!exitMenu);
    }

    public void addItemToInventory(AttributedItem item) {
        if (item instanceof ConsumableItem) {
            consumables.add((ConsumableItem) item);
        } else if (item instanceof EquippableItem) {
            equippables.add((EquippableItem) item);
        }
    }

    private <T extends AttributedItem>void attributedItemsMenu(ArrayList<T> items) {
        boolean exitMenu = false;
        do {
            input.clear();
            input.put("i", "Inspect Item");
            if (items == equippables) {
                input.put("u", "Equip Item");
            } else {
                input.put("u", "Use Item");
            }
            input.put("e", "Exit Item Menu");
            int index = 0;
            switch (IO.userInput(input)) {
                case "i":
                    index = IO.getItemIndex(items.size());
                    inspectItem(items, index);
                    break;
                case "u":
                    index = IO.getItemIndex(items.size());
                    useItem(items, index);
                    break;
                case "e":
                    exitMenu = true;
            }
        } while (!exitMenu);


    }

    public <T extends AttributedItem> void inspectItem(ArrayList<T> items, int index) {
        AttributedItem item = items.get(index);
        IO.showItemAttributes(item);
    }

    public <T extends AttributedItem> void useItem(ArrayList<T> items, int index) {
        if (items == equippables) {
            equipItem(index);
        }

        if (items == consumables) {
            consumeItem(index);
        }
    }

    public void equipItem(int index) {
        EquippableItem item = equippables.get(index - 1);
        hero.equipItem(item);

        EquipmentType et = item.getEquipmentType();
        equippedItem.put(et, item);
    }

    public void consumeItem(int index) {
        ConsumableItem item = consumables.get(index - 1);
    }

}
