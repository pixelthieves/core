package com.xkings.core.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends Component {

    private Vector3 point;

    public PositionComponent(float x, float y, float z) {
        this.point = new Vector3(x, y, z);
    }

    public PositionComponent() {
        this.point = new Vector3();
    }

    public PositionComponent(Vector3 point) {
        this.point = point.cpy();
    }

    public Vector3 getPoint() {
        return point;
    }

    public void setPoint(Vector3 point) {

        this.point = point;
    }
}
