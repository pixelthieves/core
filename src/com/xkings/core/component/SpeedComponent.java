package com.xkings.core.component;

import com.artemis.Component;

public class SpeedComponent extends Component {


    private final float speed;

    public SpeedComponent(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }
}
