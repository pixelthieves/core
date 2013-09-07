package com.xkings.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Game implements ApplicationListener {
    private OrthographicCamera camera;
    private boolean initialize = true;

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
