package com.xkings.core.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.math.Vector3;

/**
 * User: Tomas <br>
 * Date: 7/22/13 <br>
 * Time: 2:26 PM <br>
 */
public class Vector3Accessor implements TweenAccessor<Vector3> {

    // The following lines define the different possible tween types.
    // It's up to you to define what you need :-)

    public static final int VECTOR_X = 1;
    public static final int VECTOR_Y = 2;
    public static final int VECTOR_XY = 3;

    // TweenAccessor implementation

    @Override
    public int getValues(Vector3 target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case VECTOR_X:
                returnValues[0] = target.x;
                return 1;
            case VECTOR_Y:
                returnValues[0] = target.y;
                return 1;
            case VECTOR_XY:
                returnValues[0] = target.x;
                returnValues[1] = target.y;
                return 2;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Vector3 target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case VECTOR_X:
                target.x = newValues[0];
                break;
            case VECTOR_Y:
                target.y = newValues[0];
                break;
            case VECTOR_XY:
                target.x = newValues[0];
                target.y = newValues[1];
                break;
            default:
                assert false;
                break;
        }
    }
}
