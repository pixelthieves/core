package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

/**
 * Camera handler without any constrains. Created by Tomas on 9/7/13.
 */

public class BoundlessCameraHandler implements CameraHandler {
    private final float ZOOM_MAX;
    private final float ZOOM_MIN;
    protected final OrthographicCamera camera;

    public BoundlessCameraHandler(OrthographicCamera camera) {
        this(camera, 2.5f);
    }

    public BoundlessCameraHandler(OrthographicCamera camera, float ZOOM_MAX) {
        this(camera, ZOOM_MAX, 0.1f);
    }

    public BoundlessCameraHandler(OrthographicCamera camera, float ZOOM_MAX,
                                  float ZOOM_MIN) {
        this.ZOOM_MAX = ZOOM_MAX;
        this.ZOOM_MIN = ZOOM_MIN;
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

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
}