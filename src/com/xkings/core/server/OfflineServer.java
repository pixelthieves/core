package com.xkings.core.server;

import com.artemis.EntitySystem;
import com.artemis.World;
import com.xkings.core.App;
import com.xkings.core.input.CompabilityProcessInput;

public class OfflineServer extends AbstractServer {

    private final World world;
    private final CompabilityProcessInput processInput;

    public OfflineServer(App app, CompabilityProcessInput processInput) {
        super(app);
        this.processInput = processInput;
        this.world = app.getWorld();
    }


    public void addSystem(EntitySystem system) {
        world.setSystem(system);
    }

    @Override
    public void process(ClientCommand c) {
        processInput.processInput(c);
    }

    @Override
    public void updateInternal(float delta) {
        world.setDelta(delta);
        world.process();
    }
}
