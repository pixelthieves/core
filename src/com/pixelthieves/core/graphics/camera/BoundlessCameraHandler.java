package com.pixelthieves.core.graphics.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Camera handler without any constrains. Created by Tomas on 9/7/13.
 */

public class BoundlessCameraHandler implements CameraHandler {
    public final float ZOOM_MAX;
    public final float ZOOM_MIN;
    protected final OrthographicCamera camera;
    protected Vector2 halfViewport = new Vector2();

    public BoundlessCameraHandler(OrthographicCamera camera, float zoomMax, float zoomMin) {
        if (zoomMax < zoomMin) {
            throw new IllegalArgumentException("ZoomMax has to be larger than zoomMin.");
        }
        this.ZOOM_MAX = zoomMax;
        this.ZOOM_MIN = zoomMin;
        this.camera = camera;
    }

    @Override
    public void set(float x, float y) {
        camera.position.set(x * camera.zoom, y * camera.zoom, 0);
        update();
    }

    @Override
    public void move(float x, float y) {
        camera.position.add(x * camera.zoom, y * camera.zoom, 0);
        update();
    }

    @Override
    public void zoom(float zoom) {
        //TODO Choosing a good delta value is crucial, current mechanism should be improved.
        camera.zoom = MathUtils.clamp(camera.zoom + zoom * ZOOM_MAX, ZOOM_MIN, ZOOM_MAX);
        update();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public Vector2 screenToWorld(float x, float y) {
        y = Gdx.graphics.getHeight() - y;
        Vector2 vector2 = new Vector2(camera.position.x + x * camera.zoom - halfViewport.x,
                camera.position.y + y * camera.zoom - halfViewport.y);
        return vector2;
    }

    public void update() {
        halfViewport.set(camera.viewportWidth * camera.zoom / 2f, camera.viewportHeight * camera.zoom / 2f);
    }
}