package com.xkings.core.server;

import com.xkings.core.App;
import com.xkings.core.logic.Updateable;

import java.util.ArrayList;

public abstract class AbstractServer implements Updateable {
    protected App app;
    private final ArrayList<ClientCommand> buffer = new ArrayList<ClientCommand>();
    private boolean active = true;

    public AbstractServer(App app) {
        this.app = app;
    }

    public synchronized void send(ClientCommand c) {
        buffer.add(c);
    }

    @Override
    public synchronized void update(float delta) {
        for (ClientCommand c : buffer) {
            process(c);
        }
        buffer.clear();
        updateInternal(delta);
    }

    public abstract void process(ClientCommand c);

    public abstract void updateInternal(float delta);


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

}
