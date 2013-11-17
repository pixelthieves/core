package com.pixelthieves.core.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class SizeComponent extends Component {

    private final Vector3 dimensions;

    public SizeComponent(float x, float y, float z) {
        this.dimensions = new Vector3(x, y, z);
    }

    public Vector3 getPoint() {
        return dimensions;
    }

}
