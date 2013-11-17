package com.pixelthieves.core.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionOffsetComponent extends Component {

    private final Vector3 point;

    public PositionOffsetComponent(float x, float y, float z) {
        this(new Vector3(x, y, z));
    }

    public PositionOffsetComponent() {
        this(new Vector3());
    }

    public PositionOffsetComponent(Vector3 point) {
        this.point = new Vector3(point);
    }

    public Vector3 getPoint() {
        return point;
    }

}
