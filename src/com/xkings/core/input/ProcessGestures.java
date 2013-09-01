package com.xkings.core.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ProcessGestures extends GestureDetector.GestureAdapter {

    public static final float THRESHOLD = 100;
    public static final float MAX_ZOOM = 2f;
    public static final float MIN_ZOOM = 0.03f;
    private final OrthographicCamera camera;
    private float initialZoom;
    private Vector3 initialPosition;
    private boolean notSet;

    public ProcessGestures(OrthographicCamera camera) {
        this.camera = camera;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        initialZoom = camera.zoom;
        initialPosition = camera.position.cpy();
        notSet = true;
        return true;
    }


    private enum State {
        ZOOMING, MOVING
    }

    private State state = null;
    Vector2 newOriginalPoint;
    Vector2 newOriginalPoint2;

    @Override
    public boolean pinch(Vector2 originalPoint, Vector2 originalPoint2, Vector2 currentPoint, Vector2 currentPoint2) {

        float a = originalPoint.dst(currentPoint);


        if (notSet && a < 30) {
            return false;
        } else if (notSet) {
            notSet = false;
            newOriginalPoint = currentPoint.cpy();
            newOriginalPoint2 = currentPoint2.cpy();
            float ang = getAngle(originalPoint, currentPoint) * MathUtils.radiansToDegrees;
            float ang2 = getAngle(originalPoint2, currentPoint2) * MathUtils.radiansToDegrees;
            System.out.println(Math.abs(ang - ang2));
            state = Math.abs(ang - ang2) > THRESHOLD ? State.ZOOMING : State.MOVING;

        }
        float originalDistance = newOriginalPoint.dst(newOriginalPoint2);
        float currentDistance = currentPoint.dst(currentPoint2);


        switch (state) {

            case ZOOMING:
                camera.zoom = MathUtils.clamp(initialZoom * (originalDistance / currentDistance), MIN_ZOOM, MAX_ZOOM);
                break;
            case MOVING:
                Vector3 posCandidate = new Vector3();
                Vector2 diff = newOriginalPoint.cpy().sub(currentPoint);
                posCandidate.x = initialPosition.x + (diff.x * camera.zoom);
                posCandidate.y = initialPosition.y - (diff.y * camera.zoom);
                camera.position.set(posCandidate);
                break;
        }
        return false;
    }

    private float getAngle(Vector2 a, Vector2 b) {
        return (float) Math.atan2(b.y - a.y, b.x - a.x);
    }


}
