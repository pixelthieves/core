package com.xkings.core.graphics.camera;

import com.badlogic.gdx.graphics.Camera;
import com.xkings.core.graphics.Renderable;

/**
 * Created by Tomas on 10/7/13.
 */
public abstract class Renderer implements Renderable {
    protected final Camera camera;

    protected Renderer(Camera camera) {
        this.camera = camera;
    }
}
