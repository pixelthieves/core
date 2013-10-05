package com.xkings.core.generation.terrain;

/**
 * Created by Tomas on 10/4/13.
 */
public interface NoiseGenerator {
    /**
     * Generate the noise
     *
     * @return generated noise map
     */
    public Map generateMap(Map seed);
}
