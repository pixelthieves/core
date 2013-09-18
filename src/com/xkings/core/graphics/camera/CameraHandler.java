package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Common interface for handling the camera.
 * <p/>
 * Created by Tomas on 9/7/13.
 */
public interface CameraHandler {

    /**
     * Move the camera around. Camera position will be adjusted based on the zoom level of
     * camera.
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


    /**
     * Returns the wrapped camera associated with this class.
     *
     * @return the wrapped camera.
     */
    public OrthographicCamera getCamera();

}
