package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Camera handler without any constrains. Created by Tomas on 9/7/13.
 */

public class BoundlessCameraHandler implements CameraHandler {
    public final float ZOOM_MAX;
    public final float ZOOM_MIN;
    protected final OrthographicCamera camera;

    public BoundlessCameraHandler(OrthographicCamera camera, float zoomMax, float zoomMin) {
        if (zoomMax < zoomMin) {
            throw new IllegalArgumentException("ZoomMax has to be larger than zoomMin.");
        }
        this.ZOOM_MAX = zoomMax;
        this.ZOOM_MIN = zoomMin;
        this.camera = camera;
    }

    @Override
    public void move(float x, float y) {
        camera.position.add(x * camera.zoom, y * camera.zoom, 0);
    }

    @Override
    public void zoom(float zoom) {
        camera.zoom = MathUtils.clamp(camera.zoom + zoom, ZOOM_MIN, ZOOM_MAX);
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
}