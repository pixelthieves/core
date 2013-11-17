package com.pixelthieves.core.component;

import com.artemis.Component;
import com.artemis.Entity;

public class TowerHolderComponent extends Component {

    private Entity target;

    public TowerHolderComponent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
