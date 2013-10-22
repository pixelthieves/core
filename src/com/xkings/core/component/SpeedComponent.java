package com.xkings.core.component;

import com.artemis.Component;

public class SpeedComponent extends Component {

    private float speed;

    public SpeedComponent(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void scaleSpeed(float scale) {
        this.speed *= scale;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
