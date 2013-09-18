package com.xkings.core.pathfinding.astar;

/**
 * Linked nodes are backed by footprint, so they always read current value. Created by
 * Tomas on 9/15/13.
 */
public class LinkedNode extends MapNode {
    private final boolean footprint[][];

    public LinkedNode(boolean[][] footprint, int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.footprint = footprint;
    }

    @Override
    public boolean isWalkable() {
        return footprint[getxPosition()][getyPosition()];
    }
}
