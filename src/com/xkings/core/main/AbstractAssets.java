package com.xkings.core.main;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Skeletal implementation with useful methods for handling textures.
 * Created by Tomas on 9/5/13.
 */
public class AbstractAssets {
    private static HashMap<String, Array<TextureAtlas.AtlasRegion>> cachedTextures = new HashMap<String, Array<TextureAtlas.AtlasRegion>>();
    private static List<TextureAtlas> atlases = new ArrayList<TextureAtlas>();
    private static TextureAtlas current = null;
    private static int position = 1;

    /**
     * Adds atlas to available atlases.
     *
     * @param textureAtlas to be added
     */
    public void addAtlas(TextureAtlas textureAtlas) {
        atlases.add(textureAtlas);
        if (current == null) {
            current = textureAtlas;
        }
    }

    /**
     * Returns a texture region with a specific name.
     *
     * @param name of the texture
     * @return texture region in the atlas
     */
    public static TextureAtlas.AtlasRegion getTexture(String name) {
        return getTexture(name, 0);
    }

    /**
     * Returns a texture region with a specific name and animation index.
     *
     * @param name  of the texture
     * @param index specific index of animation
     * @return texture region in the atlas
     */
    public static TextureAtlas.AtlasRegion getTexture(String name, int index) {
        Array<TextureAtlas.AtlasRegion> result = getTextureArray(name);

        if (result.size > index) {
            return result.get(index);
        } else {
            throw new IllegalArgumentException("Texture [" + name + "_" + index + "] is not available.");
        }
    }

    /**
     * Returns a collection of texture regions with a specific name.
     *
     * @param name of the textures
     * @return texture region collection in the atlas
     */
    public static Array<TextureAtlas.AtlasRegion> getTextureArray(String name) {
        Array<TextureAtlas.AtlasRegion> result = cachedTextures.get(name);
        if (result == null) {
            result = current.findRegions(name);
            cachedTextures.put(name, result);
        }

        if (result != null) {
            return result;
        } else {
            throw new IllegalArgumentException("Texture [" + name + "] is not available.");
        }

    }

    /**
     * Switches to next available atlas.
     */
    public static void switchAtlas() {
        position = ++position < atlases.size() ? position : 0;
        System.out.println(position);
        current = atlases.get(position);
        cachedTextures.clear();
    }

}
