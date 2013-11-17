package com.pixelthieves.core.generation.terrain;

import java.util.Random;

/**
 * Created by Tomas on 10/3/13.
 */
public class MidpointDisplacement extends AbstractNoiseGenerator {

    public MidpointDisplacement(Random rand, float roughness, int width, int height) {
        super(rand, roughness, width, height);
    }

    @Override
    protected void generate(Map map, int x, int y, int x1, int y1) {
        int hw = (x1 - x) / 2;
        int hh = (y1 - y) / 2;
        if (hw < 1 || hh < 1) return;
        int cx = x + hw;
        int cy = y + hh;

        float ab = (map.read(x, y) + map.read(x1, y)) / 2 + getRandom();
        float bc = (map.read(x1, y) + map.read(x1, y1)) / 2 + getRandom();
        float cd = (map.read(x1, y1) + map.read(x, y1)) / 2 + getRandom();
        float da = (map.read(x, y1) + map.read(x, y)) / 2 + getRandom();

        map.write(cx, y, ab);
        map.write(x1, cy, bc);
        map.write(cx, y1, cd);
        map.write(x, cy, da);
        map.write(cx, cy, (ab + bc + cd + da) / 4 + getRandom());

        generate(map, x, y, x + hw, y + hh);
        generate(map, cx, y, cx + hw, y + hh);
        generate(map, cx, cy, cx + hw, cy + hh);
        generate(map, x, cy, x + hw, cy + hh);
    }

}