package com.pixelthieves.core.logic;

/**
 * Created by Tomas on 10/5/13.
 */
public class UpdateFilter implements Updateable {
    private final Updateable decoratedInstance;
    private float interval;
    private float timeAggregator = 0;

    public UpdateFilter(Updateable decoratedInstance, float interval) {
        this(decoratedInstance, interval, false);
    }

    public UpdateFilter(Updateable decoratedInstance, float interval, boolean imminentStart) {
        this.decoratedInstance = decoratedInstance;
        this.interval = interval;
        if (imminentStart) triggerUpdate();
    }

    @Override
    public void update(float delta) {
        timeAggregator += delta;
        while (timeAggregator >= interval) {
            timeAggregator -= interval;
            decoratedInstance.update(interval);
        }
    }

    @Override
    public boolean isActive() {
        return decoratedInstance.isActive();
    }

    @Override
    public void setActive(boolean active) {
        decoratedInstance.setActive(active);
    }

    public float getRemainingTime() {
        return interval - timeAggregator;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public void triggerUpdate() {
        timeAggregator = interval;
    }

    public void reset(){
        timeAggregator = 0;
    }
}
