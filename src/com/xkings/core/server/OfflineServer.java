package com.xkings.core.server;

import com.artemis.EntitySystem;
import com.artemis.World;

public class OfflineServer extends AbstractServer {

    private final World world;
   // private final CompabilityProcessInput processInput;

    public OfflineServer(World world/*, CompabilityProcessInput processInput*/) {
      //  this.processInput = processInput;
        this.world = world;
    }


    public void addSystem(EntitySystem system) {
        world.setSystem(system);
    }

    @Override
    public void process(ClientCommand c) {
     //   processInput.processInput(c);
    }

    @Override
    public void updateInternal(float delta) {
        world.setDelta(delta);
        world.process();
    }
}
