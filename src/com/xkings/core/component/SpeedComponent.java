package com.xkings.core.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class SpeedComponent extends Component {


    private final Vector3 speed;

    public SpeedComponent(float x, float y, float z) {
        this.speed = new Vector3(x, y, z);
    }

    public Vector3 getPoint() {
        return speed;
    }

}
