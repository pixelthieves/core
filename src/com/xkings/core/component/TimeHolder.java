package com.xkings.core.component;

/**
 * Created by Tomas on 9/8/13.
 */
public class TimeHolder {

    private float availableTime;

    public TimeHolder() {
        this(0);
    }

    public TimeHolder(float availableTime) {
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
