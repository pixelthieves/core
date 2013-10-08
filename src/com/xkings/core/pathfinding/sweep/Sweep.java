package com.xkings.core.pathfinding.sweep;

import com.xkings.core.pathfinding.GenericBlueprint;

/**
 * Moves from specific block to all directions and checks if its still possible to access these blocks.
 * <p/>
 * Created by Tomas on 9/28/13.
 */
public class Sweep {

    private final boolean canMoveDiagonally;
    private GenericBlueprint blueprint;
    private GenericBlueprint accessMap;

    public Sweep(GenericBlueprint blueprint) {
        this(blueprint, true);
    }

    public Sweep(GenericBlueprint blueprint, boolean canMoveDiagonally) {
        setBlueprint(blueprint);
        this.canMoveDiagonally = canMoveDiagonally;
    }

    public void setBlueprint(GenericBlueprint blueprint) {
        this.blueprint = blueprint;
        this.accessMap = new GenericBlueprint(blueprint.getWidth(), blueprint.getHeight());
    }

    private void clean() {
        for (int i = 0; i < accessMap.getWidth(); i++) {
            for (int j = 0; j < accessMap.getHeight(); j++) {
                accessMap.setWalkable(false, i, j);
            }
        }
    }

    /**
     * Starts search at specific coordinates
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void start(int x, int y) {
        clean();
        search(x, y);
    }

    private void search(int x, int y) {
        mark(x, y);
        searchIt(x + 1, y);
        searchIt(x - 1, y);
        searchIt(x, y + 1);
        searchIt(x, y - 1);
        if (canMoveDiagonally) {
            searchIt(x + 1, y + 1);
            searchIt(x - 1, y + 1);
            searchIt(x + 1, y - 1);
            searchIt(x - 1, y - 1);
        }
    }

    private void searchIt(int x, int y) {
        if (blueprint.inRange(x, y) && blueprint.isWalkable(x, y) && !accessMap.isWalkable(x, y)) {
            search(x, y);
        }
    }


    private void mark(int x, int y) {
        accessMap.setWalkable(true, x, y);
    }

    /**
     * Whether block is accessible from start position. <br> {@link #start(int x, int y)} must be invoked first.
     */
    public boolean isWalkable(int x, int y) {
        return blueprint.inRange(x, y) && accessMap.isWalkable(x, y);
    }

    @Override
    public String toString() {
        return accessMap.toString();
    }


}
