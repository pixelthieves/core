package com.pixelthieves.core.pathfinding;

/**
 * Created by Tomas on 10/8/13.
 */

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 9/28/13.
 */
public class GenericBlueprint<T> {

    private final T[][] data;
    private final Rectangle bounds;

    public GenericBlueprint(int width, int height) {
        this((T[][]) new Object[width][height]);
    }

    public GenericBlueprint(T[][] data) {
        this.data = data;
        bounds = new Rectangle(0, 0, data.length, data[0].length);
    }

    public boolean inRange(int x, int y) {
        return x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight();
    }

    public boolean isWalkable(int x, int y) {
        return inRange(x, y) && data[x][y] == null;
    }

    public void setWalkable(T walkable, int x, int y) {
        if (!inRange(x, y)) {
            throw new IllegalArgumentException("Position [" + x + ", " + y + "] is out of bounds.");
        }
        data[x][y] = walkable;
    }

    public T getWalkable(int x, int y) {
        if (!inRange(x, y)) {
            throw new IllegalArgumentException("Position [" + x + ", " + y + "] is out of bounds.");
        }
        return data[x][y];
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

    public void print() {
        System.out.println(print(bounds));
    }

    public String print(Rectangle segment) {
        StringBuilder builder = new StringBuilder();
        for (int i = (int) segment.x; i < segment.x + segment.width; i++) {
            for (int j = (int) segment.y; j < segment.y + segment.height; j++) {
                builder.append(data[i][j] == null ? "\u25A1" : "\u25A0");
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return print(bounds);
    }
}

