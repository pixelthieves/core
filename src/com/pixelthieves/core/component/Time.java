package com.pixelthieves.core.component;

/**
 * Created by Tomas on 9/8/13.
 */
public class Time {

    private float availableTime;

    public Time() {
        this(0);
    }

    public Time(float availableTime) {
        this.availableTime = availableTime;
    }

    public float getAvailableTime() {
        return availableTime;
    }

    public void decrease(float value) {
        if (availableTime < value) {
            throw new IllegalArgumentException("There is not enough available time to decrease");
        }
        availableTime -= value;
    }

    public void increase(float value) {
        availableTime += value;
    }
}
