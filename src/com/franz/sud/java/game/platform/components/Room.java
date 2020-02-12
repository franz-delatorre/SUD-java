package com.franz.sud.java.game.platform.components;

import com.franz.sud.java.game.platform.misc.Direction;

import java.util.EnumMap;

public abstract class Room {
    private String name;
    private Point point;
    private EnumMap<Direction, Room> adjacentRoom = new EnumMap<>(Direction.class);

    public Room(String name, Point point) {
        this.name = name;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public void setAdjacentRoom(Direction to, Room room) {
        if (adjacentRoom.get(to) != null) {
            return;
        }

        Point rm = room.getPoint();
        if (point.getxAxis() + to.getX() == rm.getxAxis()
            && point.getyAxis() + to.getY() == rm.getyAxis()
        ) {
            adjacentRoom.put(to, room);
            room.setAdjacentRoom(to.getOpposite(), this);
        }
    }

}
