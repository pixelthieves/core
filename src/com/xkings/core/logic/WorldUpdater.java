package com.xkings.core.logic;

import com.artemis.World;

/**
 * Created by Tomas on 9/6/13.
 */
public class WorldUpdater implements Updateable {
    private final World world;

    public WorldUpdater(World world) {
        this.world = world;
    }

    @Override
    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {

    }
}
