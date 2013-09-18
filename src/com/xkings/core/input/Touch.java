package com.xkings.core.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that hold information about touched point on the screen.
 */
public class Touch extends Vector2 {
    public static final float TOUCH_CLICK_DEVIATION = 40f;
    private static final int MAX_TOUCHES = 10;
    private static List<Touch> touches = Touch.getTouches();
    private final int id;
    private boolean pressedX;
    private boolean pressedY;
    private float oldX;
    private float oldY;
    private boolean moving;

    private Touch(int id) {
        super(-1f, -1f);
        this.id = id;
    }

    public static List<Touch> getTouches() {
        if (touches == null) {
            touches = new ArrayList<Touch>();
            for (int i = 0; i < MAX_TOUCHES; i++) {
                touches.add(new Touch(i));
            }
        }
        return touches;
    }

    public static int getTouching() {
        int touching = 0;
        for (Touch touch : getTouches()) {
            if (touch.isPressed()) {
                touching++;
            }
        }
        return touching;
    }

    public void pressX(float x) {
        this.x = x;
        this.oldX = x;
        pressedX = true;
    }

    public void pressY(float y) {
        this.y = y;
        this.oldY = y;
        pressedY = true;
    }

    public void moveX(float x) {
        this.x = x;
        moving = true;
    }

    public void moveY(float y) {
        this.y = y;
        moving = true;
    }

    public void releaseX(float x) {
        this.x = x;
        this.pressedX = false;
        moving = false;
    }

    public void releaseY(float y) {
        this.y = y;
        this.pressedY = false;
        moving = false;
    }

    public boolean isClicked() {
        return isRelease() && x > 0 && y > 0 && equals(oldX, x, TOUCH_CLICK_DEVIATION) &&
                equals(oldY, y, TOUCH_CLICK_DEVIATION);
    }

    private boolean equals(float a, float b, float error) {
        return Math.abs(a - b) <= error;
    }

    public boolean isMoving() {
        return moving && x > 0 && y > 0;
    }

    public boolean isRelease() {
        return !pressedX && !pressedY;
    }

    public boolean isPressed() {
        return pressedX && pressedY;
    }


    private Vector2 correctInput(OrthographicCamera camera) {
        Vector2 result = new Vector2();
        result.x = (this.x - camera.viewportWidth / 2f) * camera.zoom + camera.position.x;
        result.y =
                (this.y - camera.viewportHeight / 2f) * camera.zoom + camera.position.y;
        return result;
    }

    @Override
    public String toString() {
        return "Touch{" + "id=" + id + ", pressedX=" + pressedX + ", pressedY=" +
                pressedY + ", x=" + x + ", y=" + y + ", oldX=" + oldX + ", oldY=" + oldY +
                '}';
    }
}
