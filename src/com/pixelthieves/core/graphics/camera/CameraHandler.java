package com.pixelthieves.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Common interface for handling the camera.
 * <p/>
 * Created by Tomas on 9/7/13.
 */
public interface CameraHandler {

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
     * @param zoomDelta zooms the camera by some amount.
     */
    void zoom(float zoomDelta);


    /**
     * Returns the wrapped camera associated with this class.
     *
     * @return the wrapped camera.
     */
    public OrthographicCamera getCamera();

    /**
     * Scales input coordinated accordingly
     *
     * @return scaled vector
     */
    public Vector2 screenToWorld(float x, float y);

    /**
     * Updates viewport sizes and/or camera position.
     */
    public void update();

}
