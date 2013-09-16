package com.xkings.core.graphics.camera;

/**
 * Common interface for handling the camera.
 * <p/>
 * Created by Tomas on 9/7/13.
 */
public interface CameraHandler {
    float ZOOM_MAX = 2.5f;
    float ZOOM_MIN = 0.1f;

    /**
     * Move the camera around. Camera position will be adjusted based on the zoom level of camera.
     *
     * @param x the amount camera will be moved on x-axis.
     * @param y the amount camera will be moved on y-axis.
     */
    void move(float x, float y);

    /**
     * Zoom camera.
     *
     * @param zoom the camera to given level.
     */
    void zoom(float zoom);
}
