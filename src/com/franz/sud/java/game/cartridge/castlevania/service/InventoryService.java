package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.ConsumableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquipmentType;
import com.franz.sud.java.game.cartridge.castlevania.elements.item.EquippableItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.misc.IO;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

public class InventoryService {
    private ArrayList<ConsumableItem> consumables = new ArrayList<>();
    private ArrayList<EquippableItem> equippables = new ArrayList<>();
    private EnumMap<EquipmentType, EquippableItem> equippedItem = new EnumMap<>(EquipmentType.class);
    private Hero hero;

    public void OpenInventoryMenu() {
        HashMap<String, String> input = new HashMap<>();
        input.put("c", "Show Consumable Items");
        input.put("i", "Show Equippable Items");
        input.put("e", "Exit Inventory Menu");

        switch (IO.userInput(input)){
            case "c":
                IO.showConsumables(consumables);
                break;
            case "i":
                IO.showEquippables(equippables);
                break;
        }
    }

    public void addConsumable(ConsumableItem item) {
        consumables.add(item);
    }

    public void addEquippable(EquippableItem item) {
        equippables.add(item);
    }

    public void equipItem(EquippableItem item, Hero hero) {
        EquipmentType type = item.getEquipmentType();
        if (equippedItem.containsKey(type)) {
            EquippableItem oldItem = equippedItem.get(type);
            oldItem.unequip(hero);
        }
        equippedItem.put(type, item);
        item.useItem(hero);
    }

    public void consumeItem() {

    }
}
