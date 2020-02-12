package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.item.AttributedItem;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Enemy;
import com.franz.sud.java.game.cartridge.castlevania.elements.unit.GameUnit;
import com.franz.sud.java.game.platform.components.Room;

import java.util.HashMap;
import java.util.Map;

public class RoomService {
    private Map<Room, Enemy> enemyList = new HashMap<>();
    private Map<Room, AttributedItem> itemList = new HashMap<>();

    public boolean roomContainsEnemy(Room room) {
        if (enemyList.containsValue(room)) {
            Enemy e = enemyList.get(room);
            if (e.isAlive()) return true;
        }
        return false;
    }

    public Enemy getRoomEnemy(Room room) {
        return enemyList.get(room);
    }

    public boolean roomContainsItem(Room room) {
        return itemList.containsValue(room);
    }

    public AttributedItem getRoomItem(Room room) {
        return itemList.get(room);
    }

    public void addItem(Room room, AttributedItem item) {
        itemList.put(room, item);
    }

    public void addEnemy(Room room, Enemy enemy) {
        enemyList.put(room, enemy);
    }
}
