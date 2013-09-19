package com.xkings.core.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * User: Tomas <br> Date: 7/21/13 <br> Time: 7:49 PM <br>
 */
public class Collision {
    public static Vector2[] getRectOverLine(float x, float y, float x2, float y2, float thickness) {
        Vector2[] rect = new Vector2[4];
        int idx = 0;
        double angle = Math.atan2(y2 - y, x2 - x);
        float xOffset = (float) (Math.cos(angle + Math.PI / 2) * (thickness / 2));
        float yOffset = (float) (Math.sin(angle + Math.PI / 2) * (thickness / 2));

        rect[idx++] = new Vector2(x - xOffset, y - yOffset);
        rect[idx++] = new Vector2(x + xOffset, y + yOffset);
        rect[idx++] = new Vector2(x2 + xOffset, y2 + yOffset);
        rect[idx++] = new Vector2(x2 - xOffset, y2 - yOffset);
        return rect;
    }

    /**
     * Return true if the given point is contained inside the boundary. See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     *
     * @param point The point to check
     * @return true if the point is inside the boundary, false otherwise
     */
    public static boolean pointInsidePolygon(Vector2[] polygon, Vector2 point) {
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            if ((polygon[i].y > point.y) != (polygon[j].y > point.y) && (point.x <
                    (polygon[j].x - polygon[i].x) * (point.y - polygon[i].y) / (polygon[j].y - polygon[i].y) +
                            polygon[i].x)) {
                result = !result;
            }
        }
        return result;
    }


    public static Vector2 intersectLines(Vector2 a, Vector2 a2, Vector2 b, Vector2 b2) {
        float dxa = a.x - a2.x;
        float dxb = b.x - b2.x;
        float dya = a.y - a2.y;
        float dyb = b.y - b2.y;

        float denominator = dxa * dyb - dya * dxb;

        Vector2 result = null;
        if (denominator != 0) {
            float ta = (a.x * a2.y - a.y * a2.x);
            float tb = (b.x * b2.y - b.y * b2.x);
            result = new Vector2((ta * dxb - dxa * tb) / denominator, (ta * dyb - dya * tb) / denominator);
        }

        return result;
    }
}
