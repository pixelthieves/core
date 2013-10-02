package com.xkings.core.component;

import com.artemis.Component;

public class RangeComponent extends Component {

    private final float range;

    public RangeComponent(float range) {
        this.range = range;
    }

    public float getRange() {
        return range;
    }
}
