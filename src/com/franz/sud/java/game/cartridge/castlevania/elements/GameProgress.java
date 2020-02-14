package com.franz.sud.java.game.cartridge.castlevania.elements;

import com.franz.sud.java.game.platform.components.Room;

import java.util.ArrayList;

public class GameProgress {
    private int progress;
    private ArrayList<ArrayList<Room>> progressRooms = new ArrayList<>();

    public GameProgress() {
        progress = 0;
    }

    public void addProgressRooms(ArrayList<Room> openRooms) {
        progressRooms.add(openRooms);
    }

    public ArrayList<Room> getCurrentProgressRooms() {
        return progress < progressRooms.size() ? progressRooms.get(progress) : null;
    }

    public Room getProgressBossRoom() {
        ArrayList<Room> rooms = progressRooms.get(progress);
        return rooms.get(rooms.size() - 1);
    }

    public int getProgress() {
        return progress;
    }

    public void incrementProgress() {
        progress++;
    }
}
