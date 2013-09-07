package com.xkings.core.main;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tomas on 9/5/13.
 */
public class AbstractAssets {
    private static HashMap<String, Array<TextureAtlas.AtlasRegion>> cachedTextures = new HashMap<String, Array<TextureAtlas.AtlasRegion>>();
    private static List<TextureAtlas> atlases = new ArrayList<TextureAtlas>();
    private static TextureAtlas current = null;
    private static int position = 1;

    public void addAtlas(TextureAtlas textureAtlas) {
        atlases.add(textureAtlas);
        if (current == null) {
            current = textureAtlas;
        }
    }

    public static TextureAtlas.AtlasRegion getTexture(String name) {
        return getTexture(name, 0);
    }


    public static TextureAtlas.AtlasRegion getTexture(String name, int index) {
        Array<TextureAtlas.AtlasRegion> result = getTextureArray(name);

        if (result.size > index) {
            return result.get(index);
        } else {
            throw new IllegalArgumentException("Texture [" + name + "_" + index + "] is not available.");
        }
    }

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


    public static void switchAtlas() {
        position = ++position < atlases.size() ? position : 0;
        System.out.println(position);
        current = atlases.get(position);
        cachedTextures.clear();
    }

}
