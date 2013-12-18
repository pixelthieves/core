package com.pixelthieves.core.logic;

/**
 * Created by Tomas on 10/5/13.
 */
public class UpdateFilter implements Updateable {
    private final Updateable decoratedInstance;
    private final int iterations;
    private boolean running;
    private float interval;
    private float timeAggregator = 0;
    private int currentIterations;

    public UpdateFilter(Updateable decoratedInstance, float interval) {
        this(decoratedInstance, interval, -1);
    }

    public UpdateFilter(Updateable decoratedInstance, float interval, int iterations) {
        this(decoratedInstance, interval, iterations, false);
    }


    public UpdateFilter(Updateable decoratedInstance, float interval, int iterations, boolean imminentStart) {
        this.decoratedInstance = decoratedInstance;
        this.interval = interval;
        this.iterations = iterations;
        this.running = true;
        if (imminentStart) triggerUpdate();
    }

    @Override
    public void update(float delta) {
        if (running) {
            timeAggregator += delta;
            while (timeAggregator >= interval) {
                timeAggregator -= interval;
                decoratedInstance.update(interval);
                currentIterations++;
            }
            if (iterations != -1 && currentIterations >= iterations) {
                running = false;
                if (listener != null) {
                    listener.onEnd();
                }
            }
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
        update(getRemainingTime());
    }

    public void reset() {
        timeAggregator = 0;
        currentIterations = 0;
    }

    public void reset(float interval) {
        setInterval(interval);
        reset();
    }

    private Listener listener;

    public interface Listener {
        void onEnd();
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
