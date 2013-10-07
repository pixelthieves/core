package com.xkings.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.xkings.core.utils.Param;
import com.xkings.core.utils.ParamHolder;

/**
 * Skeletal implementation for a 2D Game
 */

public abstract class Game2D implements ApplicationListener {
    private final ParamHolder params;
    private OrthographicCamera camera;
    private boolean initialize = true;
    public static Param DEBUG;

    protected Game2D(String... args) {
        params = new ParamHolder(args);
        DEBUG = params.getParam("-debug", "-d");
    }

    @Override
    public void create() {
    }


    @Override
    public void render() {
        if (!initialize) {
            camera.update();
            renderInternal();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        if (initialize) {
            init(camera);
            initialize = false;
        }
    }

    protected abstract void renderInternal();

    protected abstract void init(OrthographicCamera camera);

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
