package com.xkings.core.component;

import com.artemis.Component;
import com.artemis.Entity;

public class TargetComponent extends Component {

    private Entity target;

    public TargetComponent(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }
}
