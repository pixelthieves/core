package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Camera can be only operated inside of bounds. If camera is brought outside of the bounds, it's position is adjusted
 * so, camera's viewport aligned with bounds. Created by Tomas on 9/7/13.
 */
public class BoundedCameraHandler extends BoundlessCameraHandler {
    private final Rectangle bounds;

    public BoundedCameraHandler(OrthographicCamera camera, int width, int height, float zoomMax) {
        this(camera, new Rectangle(0, 0, width, height), zoomMax);
    }

    public BoundedCameraHandler(OrthographicCamera camera, Rectangle bounds, float zoomMax) {
        super(camera, getMinimalZoom(camera, bounds), zoomMax);
        if (bounds == null) throw new IllegalArgumentException("Bounds can't be null");
        this.bounds = bounds;
        zoom(camera.zoom);
    }

    private static float getMinimalZoom(OrthographicCamera camera, Rectangle bounds) {
        return Math.min(bounds.getWidth() / (camera.viewportWidth * camera.zoom),
                bounds.getHeight() / (camera.viewportHeight * camera.zoom));
    }

    @Override
    public void update() {
        super.update();
        camera.position.x = MathUtils.clamp(camera.position.x, halfViewport.x, bounds.width - halfViewport.x);
        camera.position.y = MathUtils.clamp(camera.position.y, halfViewport.y, bounds.height - halfViewport.y);
    }
}
