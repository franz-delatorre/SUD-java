package com.franz.sud.java.game.cartridge.castlevania.elements;

import com.franz.sud.java.game.cartridge.castlevania.elements.unit.Hero;
import com.franz.sud.java.game.misc.Direction;
import com.franz.sud.java.game.platform.components.Point;
import com.franz.sud.java.game.platform.components.Room;

import java.util.ArrayList;

import static com.franz.sud.java.game.misc.TextColor.ANSI_GREEN;
import static com.franz.sud.java.game.misc.TextColor.ANSI_BLACK;
import static com.franz.sud.java.game.misc.TextColor.ANSI_RESET;

public class GameMap {
    private ArrayList<Room> openRooms = new ArrayList<>();
    private Room heroPreviousLocation;
    private Hero hero;

    public GameMap(Hero hero) {
        this.hero = hero;
        heroPreviousLocation = hero.getCurrentLocation();
    }

    /**
     * Sets the openRooms for the current map. This is used during the
     * progression of the game.
     * @param openRooms
     */
    public void setOpenRooms(ArrayList<Room> openRooms) {
        this.openRooms = openRooms;
    }

    public Room getHeroLocation() {
        return hero.getCurrentLocation();
    }

    public void setHeroLocation(Room rm) {
        hero.setCurrentLocation(rm);
    }

    public void setPreviousRoom(Room rm) {
        heroPreviousLocation = rm;
    }

    /**
     * Shows the graphical location of the hero. Also shows the rooms
     * available in the game.
     */
    public void showMap() {
        System.out.println();
        // Index 0 for min range and index 1 for max range
        int[] xRange = {0, 0};
        int[] yRange = {0, 0};

        //Sets the x and y axis range of the map.
        for (Room rm : openRooms) {
            Point rmPoint = rm.getPoint();
            xRange = getAxisRange(xRange, rmPoint.getxAxis());
            yRange = getAxisRange(yRange, rmPoint.getyAxis());
        }

        System.out.println(ANSI_RESET +  "=================================" + ANSI_BLACK);
        for (int y = yRange[1]; y >= yRange[0]; y--) {
            for (int x = xRange[0]; x <= xRange[1]; x++) {

                //Checks if the room exist at the specified point
                if (roomExist(new Point(x, y))) {
                    Point heroPoints = hero.getCurrentLocation().getPoint();

                    //Checks if the hero is at the current specified point
                    if (heroPoints.getyAxis() == y && heroPoints.getxAxis() == x) {
//                        System.out.printf(TextColor.ANSI_GREEN + "[ * ]" + TextColor.ANSI_BLACK);
                        System.out.printf("[ " + ANSI_GREEN + "*" + ANSI_BLACK + " ]");
                    } else {
                        System.out.printf("[   ]");
                    }
                } else {
                    System.out.printf("\t  ");
                }
            }
            System.out.println("\n");
        }

        System.out.println("      == "+ ANSI_RESET + hero.getCurrentLocation().getName() + ANSI_BLACK + " ==      ");
        System.out.println(ANSI_RESET +  "=================================" + ANSI_BLACK);
    }

    /**
     * Gets the min and max range of each the axis. Index 0 is the min and index 1 is the
     * max range.
     * @param range
     * @param axis
     * @return
     */
    private int[] getAxisRange(int[] range, int axis) {
        int min = range[0];
        int max = range[1];
        if (axis < range[0]) min = axis;
        if (axis > range[1]) max = axis;
        return new int[] {min, max};
    }

    /**
     * Checks if the point exist in the list of rooms.
     * @param pt
     * @return
     */
    private boolean roomExist(Point pt) {
        for (Room room: openRooms) {
            Point rm  = room.getPoint();
            if (rm.getxAxis() == pt.getxAxis() && rm.getyAxis() == pt.getyAxis()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Will return true if the adjacent direction's room is in the list of
     * opened rooms in the game map.
     * @param to
     * @param room
     * @return
     */
    public boolean canMoveToAdjacentRoom (Direction to, Room room) {
        Room adjRm = room.getAdjacentRoom(to);
        return openRooms.contains(adjRm);
    }

    /**
     * Sets the hero's location equal to the previous location.
     */
    public void retractHeroLocation() {
        setHeroLocation(heroPreviousLocation);
    }
}
