package com.pixelthieves.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pixelthieves.core.utils.Param;
import com.pixelthieves.core.utils.ParamHolder;

/**
 * Skeletal implementation for a 2D Game
 */

public abstract class Game2D implements ApplicationListener {
    private final ParamHolder params;
    private OrthographicCamera camera;
    private boolean initialize = true;
    public static Param DEBUG;
    public static Param STRESS_TEST;

    protected Game2D(String... args) {
        params = new ParamHolder(args);
        DEBUG = params.getParam("-debug", "-d");
        STRESS_TEST = params.getParam("-stress", "-stresstest", "-st");
    }

    @Override
    public void create() {
        System.err.println("[CREATE]");
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
        if (camera == null) {
            camera = new OrthographicCamera(width, height);
        }
        camera.position.set(width / 2, height / 2, 0);
        if (initialize) {
            init(camera);
            initialize = false;
        }
    }

    public void restart(){
        this.initialize = true;
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    protected abstract void renderInternal();

    protected abstract void init(OrthographicCamera camera);

    @Override
    public void pause() {
        System.err.println("[PAUSE]");
    }

    @Override
    public void resume() {
        System.err.println("[RESUME]");
    }

    @Override
    public void dispose() {
        System.err.println("[DISPOSE]");
    }
}
