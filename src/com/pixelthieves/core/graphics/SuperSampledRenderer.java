package com.pixelthieves.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.graphics.Pixmap.Format.RGB565;

/**
 * Renderer wrapper that writes to larger buffer and them scales it down. User: Tomas <br> Date: 7/19/13 <br> Time: 3:57
 * AM <br>
 */
public class SuperSampledRenderer implements Renderable {
    private final Renderable renderer;
    private static final float FACTOR = 1.5f;
    private FrameBuffer frameBufferObject;
    private TextureRegion frameBufferRegion;
    private SpriteBatch spriteBatch;

    /**
     * Constructs a renderer with appropriate wrapped renderer.
     *
     * @param renderer to be wrapped around
     */
    public SuperSampledRenderer(Renderable renderer) {
        this(renderer, FACTOR);
    }

    /**
     * Constructs a renderer with appropriate wrapped renderer and specific scale factor. <ul> <li>Scale factor greater
     * than 1 increases quality, but decreases performance.</li> <li>Scale factor smaller than 1 decreases quality, but
     * increases performance.</li> </ul>
     *
     * @param renderer to be wrapped around
     * @param factor   scale factor used for determining the buffer size
     */
    public SuperSampledRenderer(Renderable renderer, float factor) {
        this.renderer = renderer;
        spriteBatch = new SpriteBatch();

        frameBufferObject =
                new FrameBuffer(RGB565, (int) (graphics.getWidth() * factor), (int) (graphics.getHeight() * FACTOR),
                        false);
        frameBufferRegion = new TextureRegion(frameBufferObject.getColorBufferTexture());
        frameBufferRegion.flip(false, true);
    }

    @Override
    public void render() {
        frameBufferObject.begin();
        renderer.render();
        frameBufferObject.end();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
                (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        spriteBatch.begin();
        spriteBatch.draw(frameBufferRegion, 0, 0, graphics.getWidth(), graphics.getHeight());
        spriteBatch.end();
    }
}