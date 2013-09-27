package com.xkings.core.pathfinding;

import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.utils.ArrayUtils;

/**
 * Created by Tomas on 9/28/13.
 */
public class Blueprint {

    private final boolean[][] data;

    public Blueprint(int width, int height) {
        this(new boolean[width][height]);
    }

    public Blueprint(boolean[][] data) {
        this.data = data;
    }

    public boolean[][] getData() {
        return data;
    }

    public String print(Rectangle segment) {
        StringBuilder builder = new StringBuilder();
        for (int i = (int) segment.x; i < segment.x + segment.width; i++) {
            for (int j = (int) segment.y; j < segment.y + segment.height; j++) {
                builder.append(data[i][j] ? "\u25A1" : "\u25A0");
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public int getWidth() {
        return data.length;
    }

    public int getHeight() {
        return data[0].length;
    }

    public Rectangle getBounds() {
        return new Rectangle(0, 0, data.length, data[0].length);
    }

    public static Blueprint createInstanceClone(boolean[][] originalData) {
        boolean[][] data = new boolean[originalData.length][originalData[0].length];
        ArrayUtils.copyArray(originalData, data);
        return new Blueprint(data);
    }
}
