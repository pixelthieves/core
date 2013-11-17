package com.pixelthieves.core.generation.terrain;

import java.util.Random;

/**
 * Created by Tomas on 10/3/13.
 */
public class DiamondSquare extends AbstractNoiseGenerator {

    public DiamondSquare(Random rand, float roughness, int width, int height) {
        super(rand, roughness,width,  height);
    }

    @Override
    protected void generate(Map map, int x, int y, int w, int h) {
        int level = 1;
        double rough = roughness;

        while (level < w && level < h) {

            int width = w / level;
            int height = h / level;

            //diamond phase
            for (int i = 0; i < level; i++) {
                for (int j = 0; j < level; j++) {
                    int px = i * width;
                    int py = j * height;
                    int px1 = (i + 1) * width;
                    int py1 = (j + 1) * height;
                    int cx = px + width / 2;
                    int cy = py + height / 2;


                    float a = map.read(px, py);
                    float b = map.read(px1, py);
                    float c = map.read(px1, py1);
                    float d = map.read(px, py1);
                    float mean = (a + b + c + d) / 4;
                    map.write(cx, cy, (float) (mean + getRandom() * rough));
                }
            }

            //square phase
            for (int i = 0; i < level; i++) {
                for (int j = 0; j < level; j++) {

                    int px = i * width;
                    int py = j * height;
                    int px1 = (i + 1) * width;
                    int py1 = (j + 1) * height;
                    int cx = px + width / 2;
                    int cy = py + height / 2;

                    int cxLeft = cx - width;
                    int cxRight = cx + width;
                    int cyDown = cy - height;
                    int cyUp = cy + height;

                    float a = map.read(px, py);
                    float b = map.read(px1, py);
                    float c = map.read(px1, py1);
                    float d = map.read(px, py1);
                    float e = map.read(cx, cy);


                    float mean = e + d + map.read(cxLeft, cy) + a;
                    map.write(px, cy, (float) (mean / 4 + getRandom() * rough));

                    mean = e + a + map.read(cx, cyDown) + b;
                    map.write(cx, py, (float) (mean / 4 + getRandom() * rough));

                    mean = e + b + map.read(cxRight, cy) + c;
                    map.write(px1, cy, (float) (mean / 4 + getRandom() * rough));

                    mean = e + c + map.read(cx, cyUp) + d;
                    map.write(cx, py1, (float) (mean / 4 + getRandom() * rough));
                }
            }
            level *= 2;
            rough *= roughness;
        }
    }


}