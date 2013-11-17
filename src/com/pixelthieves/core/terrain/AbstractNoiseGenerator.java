package com.pixelthieves.core.generation.terrain;

import java.util.Random;

/**
 * Skeleton class for {@link NoiseGenerator} implementations.
 * <p/>
 * Created by Tomas on 10/4/13.
 */
public abstract class AbstractNoiseGenerator implements NoiseGenerator {
    /**
     * Width of map
     */
    protected final int width;
    /**
     * Height of map
     */
    protected final int height;
    /**
     * Source of entropy
     */
    protected Random random;
    /**
     * Roughness of noise
     */
    protected final float roughness;

    /**
     * Generate a noise source based upon the midpoint displacement fractal.
     *
     * @param rand      The random number generator
     * @param roughness the roughness of noise
     * @param width     the width of the grid
     * @param height    the height of the grid
     */
    public AbstractNoiseGenerator(Random rand, float roughness, int width, int height) {
        this.random = (rand == null) ? new Random() : rand;
        this.width = width;
        this.height = height;
        this.roughness = roughness;
    }

    public synchronized Map generateMap(Map seed) {
        Map map = new Map(width, height);
        fillMap(seed, map);
        generate(map, 0, 0, width - 1, height - 1);
        return map;
    }

    private void fillMap(Map seed, Map map) {
        if (seed != null) {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    map.write(i, j, seed.read(i, j));
                }
            }
        }
    }

    protected abstract void generate(Map map, int x, int y, int x1, int y1);

    protected float getRandom() {
        return (2f * random.nextFloat() - 1.0f) * roughness;
    }
}
