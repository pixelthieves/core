package com.xkings.core.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * User: Tomas <br>
 * Date: 7/22/13 <br>
 * Time: 2:26 PM <br>
 */
public class CameraAccessor implements TweenAccessor<OrthographicCamera> {

    // The following lines define the different possible tween types.
    // It's up to you to define what you need :-)

    public static final int ZOOM = 1;

    // TweenAccessor implementation

    @Override
    public int getValues(OrthographicCamera target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case ZOOM:
                returnValues[0] = target.zoom;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(OrthographicCamera target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case ZOOM:
                target.zoom = newValues[0];
                break;
            default:
                assert false;
                break;
        }
    }
}
