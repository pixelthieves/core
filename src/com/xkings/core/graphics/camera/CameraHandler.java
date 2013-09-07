package com.xkings.core.graphics.camera;

/**
 * Created by Tomas on 9/7/13.
 */
public interface CameraHandler {
    float ZOOM_MAX = 2.5f;
    float ZOOM_MIN = 0.1f;

    void move(float x, float y);

    void zoom(float zoom);
}
