package com.franz.sud.java.game.cartridge.castlevania.service;

import com.franz.sud.java.game.cartridge.castlevania.elements.GameMap;
import com.franz.sud.java.game.misc.Direction;
import com.franz.sud.java.game.misc.IO;
import com.franz.sud.java.game.platform.components.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class MapService {
    private GameMap map;

    public MapService() {
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void setOpenRooms(ArrayList<Room> rm) {
        map.setOpenRooms(rm);
    }

    /**
     * Will move the character around the map. Users cannot move to the adjacent room if
     * it is not listed in the open rooms of the current map used.
     */
    public int mapMenu() {
        boolean heroMoved = false;
        map.showMap();
        System.out.println();

        Room currRoom = map.getHeroLocation();

        HashMap<String, String> input = new HashMap<>();
        if (map.canMoveToAdjacentRoom(Direction.NORTH, currRoom)) input.put("w", "Move up");
        if (map.canMoveToAdjacentRoom(Direction.SOUTH, currRoom)) input.put("s", "Move down");
        if (map.canMoveToAdjacentRoom(Direction.EAST, currRoom)) input.put("d", "Move right");
        if (map.canMoveToAdjacentRoom(Direction.WEST, currRoom)) input.put("a", "Move left");
        input.put("e", "Exit");

        switch (IO.userInput(input)) {
            case "w":
                moveTo(Direction.NORTH);
                heroMoved = true;
                break;
            case "s":
                moveTo(Direction.SOUTH);
                heroMoved = true;
                break;
            case "a":
                moveTo(Direction.WEST);
                heroMoved = true;
                break;
            case "d":
                moveTo(Direction.EAST);
                heroMoved = true;
                break;
            case "e":
        }
        if (heroMoved) return 0;
        return 1;
    }

    /**
     * Moves the unit to the specified direction. If the room is not avaialble
     * it will alert the player and requests a new input for another room;
     * @param to
     */
    private boolean moveTo(Direction to) {
        Room rm = map.getHeroLocation();
        if (map.canMoveToAdjacentRoom(to, rm)) {
            map.setPreviousRoom(rm);
            map.setHeroLocation(rm.getAdjacentRoom(to));
            return true;
        }
        else {
            return false;
//            System.out.println(ANSI_RED + "Wrong input, try again" + ANSI_BLACK);
        }
    }

    public void resetHeroLocation() {
        map.retractHeroLocation();
    }
}
