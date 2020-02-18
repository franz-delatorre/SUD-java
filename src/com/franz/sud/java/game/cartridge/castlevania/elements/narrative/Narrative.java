package com.franz.sud.java.game.cartridge.castlevania.elements.narrative;

import java.util.ArrayList;

public class Narrative {
    private ArrayList<String[]> narrative = new ArrayList<>(2);
    private boolean isNarrated = false;

    public Narrative() {
    }

    public void addNarrative(String[] s) {
        narrative.add(s);
    }

    public String[] getNarrative(int index) {
        if (narrative.size() == 1 || index == 1) isNarrated = true;
        if (index > narrative.size() - 1) {
            narrative.add(new String[]{});
        }
        return narrative.get(index);
    }

    public boolean isNarrated() {
        return isNarrated;
    }

    public void setNarrated(boolean narrated) {
        isNarrated = narrated;
    }
}
