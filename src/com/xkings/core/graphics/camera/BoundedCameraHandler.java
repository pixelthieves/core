package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Camera can be only operated inside of bounds. If camera is brought outside of the bounds, it's position is adjusted so, camera's viewport aligned with bounds.
 * Created by Tomas on 9/7/13.
 */
public class BoundedCameraHandler extends BoundlessCameraHandler {
    private final Rectangle bounds;

    public BoundedCameraHandler(OrthographicCamera camera, Rectangle bounds) {
        super(camera);
        if (bounds == null) throw new IllegalArgumentException("Bounds can't be null");
        this.bounds = bounds;
        toCenter();
    }

    private void toCenter() {
        camera.position.x = camera.viewportWidth / 2f;
        camera.position.y = camera.viewportHeight / 2f;
    }

    public void move(float x, float y) {
        super.move(x, y);
        fixBounds();
    }

    public void zoom(float zoom) {
        super.zoom(zoom);
        fixBounds();
    }

    private void fixBounds() {
        Vector2 halfViewport = new Vector2(camera.viewportWidth * camera.zoom / 2f, camera.viewportHeight * camera.zoom / 2f);
        camera.position.x = MathUtils.clamp(camera.position.x, halfViewport.x, bounds.width - halfViewport.x);
        camera.position.y = MathUtils.clamp(camera.position.y, halfViewport.y, bounds.height - halfViewport.y);
    }
}
