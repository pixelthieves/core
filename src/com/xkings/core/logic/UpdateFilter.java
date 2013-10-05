package com.xkings.core.logic;

/**
 * Created by Tomas on 10/5/13.
 */
public class UpdateFilter implements Updateable {
    private final Updateable decoratedInstance;
    private final float timeFilter;
    private float timeAggregator = 0;

    public UpdateFilter(Updateable decoratedInstance, float timeFilter) {
        this.decoratedInstance = decoratedInstance;
        this.timeFilter = timeFilter;
    }

    @Override
    public void update(float delta) {
        timeAggregator += delta;
        while (timeAggregator <= timeFilter) {
            timeAggregator -= timeFilter;
            decoratedInstance.update(timeFilter);
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
}
