package com.xkings.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.graphics.Pixmap.Format.RGB565;

/**
 * User: Tomas <br>
 * Date: 7/19/13 <br>
 * Time: 3:57 AM <br>
 */
public class SuperSampledRenderer implements Renderable {
    private final Renderable renderer;
    private static final float FACTOR = 1.5f;
    private FrameBuffer frameBufferObject;
    private TextureRegion frameBufferRegion;
    private SpriteBatch spriteBatch;

    public SuperSampledRenderer(Renderable renderer) {
        this.renderer = renderer;
        spriteBatch = new SpriteBatch();

        frameBufferObject = new FrameBuffer(RGB565, (int) (graphics.getWidth() * FACTOR), (int) (graphics.getHeight() * FACTOR), false);
        frameBufferRegion = new TextureRegion(frameBufferObject.getColorBufferTexture());
        frameBufferRegion.flip(false, true);
    }

    @Override
    public void render() {
        frameBufferObject.begin();
        renderer.render();
        frameBufferObject.end();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        spriteBatch.begin();
        spriteBatch.draw(frameBufferRegion, 0, 0, graphics.getWidth(), graphics.getHeight());
        spriteBatch.end();
    }
}