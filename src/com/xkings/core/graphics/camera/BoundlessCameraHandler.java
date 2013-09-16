package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Camera handler without any constrains.
 * Created by Tomas on 9/7/13.
 */

public class BoundlessCameraHandler implements CameraHandler {
    protected final OrthographicCamera camera;

    public BoundlessCameraHandler(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void move(float x, float y) {
        camera.position.add(x * camera.zoom, y * camera.zoom, 0);
    }

    @Override
    public void zoom(float zoom) {
        camera.zoom = MathUtils.clamp(zoom, ZOOM_MIN, ZOOM_MAX);
    }

    /**
     * Returns the wrapped camera associated with this class.
     *
     * @return the wrapped camera.
     */
    public OrthographicCamera getCamera() {
        return camera;
    }
}