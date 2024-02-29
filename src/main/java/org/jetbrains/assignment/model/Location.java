package org.jetbrains.assignment.model;

public record Location(int x, int y) {

    public static Location start() {
        return new Location(0, 0);
    }

    public Location move(Move move) {
        int steps = move.steps();
        return switch (move.direction()) {
            case NORTH -> new Location(x, y + steps);
            case EAST -> new Location(x + steps, y);
            case SOUTH -> new Location(x, y - steps);
            case WEST -> new Location(x - steps, y);
        };
    }

    public Move findMove(Location other) {
        if (x > other.x) {
            return new Move(Direction.WEST, other.x + x);
        }
        if (x < other.x) {
            return new Move(Direction.EAST, other.x - x);
        }
        if (y > other.y) {
            return new Move(Direction.SOUTH, other.y + y);
        }
        if (y < other.y) {
            return new Move(Direction.NORTH, other.y - y);
        }
        return null;
    }
}