package com.xkings.core.input;

import com.badlogic.gdx.input.GestureDetector;

/**
 * GestureDetector with added possibility to control zoom by scrolling. Created by Tomas
 * on 9/18/13.
 */
//TODO Zooming is not really gesture at all, should be fixed.
public class EnhancedGestureDetector extends GestureDetector {
    public static final int ZOOM_SCROLL_FACTOR = 10;
    private final GestureListener gestureListener;

    public EnhancedGestureDetector(GestureListener gestureListener) {
        super(gestureListener);
        this.gestureListener = gestureListener;
    }

    @Override
    public boolean scrolled(int amount) {
        return gestureListener.zoom(0, amount * ZOOM_SCROLL_FACTOR);
    }
}
