package com.pixelthieves.core.component;

import com.artemis.Component;

public class RangeComponent extends Component {

    private final float range;
    private boolean visible;

    public RangeComponent(float range) {
        this(range, false);

    }

    public RangeComponent(float range, boolean visible) {
        this.range = range;
        this.visible = visible;
    }

    public float getRange() {
        return range;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
