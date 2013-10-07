package com.xkings.core.entity;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Skeletal class for all entities, some of the work, such is creating the entity, is already done.
 */
public abstract class ConcreteEntity {
    private final List<Component> bag = new ArrayList<Component>();
    private final World world;
    protected Entity entity;

    public ConcreteEntity(World world) {
        this.world = world;
    }

    /**
     * Add entity component.
     *
     * @param component to be added.
     */
    public void addComponent(Component component) {
        bag.add(component);
    }

    private Entity createEntity() {
        if (entity == null) {
            entity = world.createEntity();
            for (Component c : bag) {
                entity.addComponent(c);
            }
        }
        return entity;
    }

    /**
     * Register entity into world.
     */
    public void register() {
        this.createEntity().addToWorld();
    }

    /**
     * Delete entity from the world.
     */
    public void deleteFromWorld() {
        entity.deleteFromWorld();
    }
}
