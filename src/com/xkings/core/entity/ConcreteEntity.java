package com.xkings.core.entity;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteEntity {
    protected final List<Component> bag = new ArrayList<Component>();
    private final World world;
    private Entity entity;
    private boolean registered;

    public ConcreteEntity(World world) {
        this.world = world;
    }

    public Entity createEntity() {
        if (entity == null) {
            entity = world.createEntity();
            for (Component c : bag) {
                entity.addComponent(c);
            }
        }
        return entity;
    }

    public void register() {
        this.createEntity().addToWorld();
        registered = true;
    }

    public void deleteFromWorld() {
        entity.deleteFromWorld();
    }

    public boolean isRegistered() {
        return registered;
    }
}
