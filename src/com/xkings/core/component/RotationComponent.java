package com.xkings.core.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class RotationComponent extends Component {


    private final Vector3 rotation;

    public RotationComponent() {
        this(0, 0, 0);
    }

    public RotationComponent(float x, float y, float z) {
        this.rotation = new Vector3(x, y, z);
    }

    public Vector3 getPoint() {
        return rotation;
    }

}
