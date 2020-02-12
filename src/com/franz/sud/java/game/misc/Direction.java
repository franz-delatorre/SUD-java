package com.franz.sud.java.game.misc;

public enum Direction {
    NORTH(0, 1) {
        @Override
        public Direction getOpposite() {
            return SOUTH;
        }
    },
    WEST(-1,0) {
        @Override
        public Direction getOpposite() {
            return EAST;
        }
    },
    SOUTH(0,-1) {
        @Override
        public Direction getOpposite() {
            return NORTH;
        }
    },
    EAST(1,0) {
        @Override
        public Direction getOpposite() {
            return WEST;
        }
    };

    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction getOpposite() {
        return null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
