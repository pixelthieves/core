package com.xkings.core.generation.terrain;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/3/13.
 */
public class Map {
    private final float[][] map;
    private final boolean wrap;
    private final Rectangle bounds;
    private float minimum = Float.MAX_VALUE;
    private float maximum = Float.MIN_VALUE;

    public Map(int width, int height) {
        this(width, height, true);
    }

    public Map(int width, int height, boolean wrap) {
        this.map = new float[width][height];
        this.wrap = wrap;
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public int getWidth() {
        return map.length;
    }

    public int getHeight() {
        return map[0].length;
    }

    public void write(int x, int y, float value) {
        if (wrap) {
            this.map[wrapX(x)][wrapY(y)] = value;
        } else {
            this.map[x][y] = value;
        }
        minimum = value < minimum ? value : minimum;
        maximum = value > maximum ? value : maximum;
    }


    private int wrapX(int x) {
        if (x < 0) {
            return x + this.getWidth();
        } else if (x >= this.getWidth()) {
            return x - this.getWidth();
        } else {
            return x;
        }
    }

    private int wrapY(int y) {
        if (y < 0) {
            return y + this.getHeight();
        } else if (y >= this.getHeight()) {
            return y - this.getHeight();
        } else {
            return y;
        }
    }

    public float read(int x, int y) {
        if (wrap) {
            return this.map[wrapX(x)][wrapY(y)];
        } else {
            return this.map[x][y];
        }
    }

    public float getCenterX() {
        return this.getWidth() / 2f;
    }

    public float getCenterY() {
        return this.getHeight() / 2f;
    }

    public void scale(int newMinimum, int newMaximum) {
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                this.map[i][j] =
                        (this.map[i][j] - minimum) * (newMaximum - newMinimum) / (maximum - minimum) + newMinimum;
            }
        }
    }


    public String print() {
        return print(bounds);
    }

    public String print(Rectangle segment) {
        StringBuilder builder = new StringBuilder();
        for (int i = (int) segment.x; i < segment.x + segment.width; i++) {
            for (int j = (int) segment.y; j < segment.y + segment.height; j++) {
                builder.append(String.format("%.2f", map[i][j]));
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return print();
    }
}
