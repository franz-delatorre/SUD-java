package com.franz.sud.java.game.cartridge.castlevania.elements;

import com.franz.sud.java.game.platform.components.Point;
import com.franz.sud.java.game.platform.components.Room;

public class GameRoom extends Room {
    private boolean roomItemAvailable;
    private boolean roomNarrative;

    public GameRoom(String name, Point point) {
        super(name, point);
    }
}
