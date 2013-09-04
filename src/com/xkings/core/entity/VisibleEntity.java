package com.xkings.core.entity;

import com.artemis.World;
import com.xkings.core.component.VisibleComponent;

/**
 * User: Tomas <br>
 * Date: 7/18/13 <br>
 * Time: 8:03 PM <br>
 */
public abstract class VisibleEntity extends ConcreteEntity {
    private final VisibleComponent visibleComponent;

    public VisibleEntity(World world, boolean visible) {
        super(world);
        visibleComponent = new VisibleComponent(visible);
    }

    @Override
    public void register() {
        bag.add(visibleComponent);
        super.register();
    }

    public void setVisible(boolean visible) {
        visibleComponent.setVisible(visible);
    }
}
