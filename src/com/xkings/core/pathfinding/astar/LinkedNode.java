package com.xkings.core.pathfinding.astar;

import com.xkings.core.pathfinding.GenericBlueprint;

/**
 * Linked nodes are backed by blueprint, so they always read current value. Created by Tomas on 9/15/13.
 */
public class LinkedNode extends MapNode {
    private final GenericBlueprint blueprint;

    public LinkedNode(GenericBlueprint blueprint, int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.blueprint = blueprint;
    }

    @Override
    public boolean isWalkable() {
        return blueprint.isWalkable(getxPosition(), getyPosition());
    }
}
