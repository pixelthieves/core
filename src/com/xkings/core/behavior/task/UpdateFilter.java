package com.xkings.core.behavior.task;


import com.artemis.Entity;
import com.xkings.core.behavior.Ticker;


/**
 * Decorator that adds a update speed limit to the task it is applied to
 *
 */
public class UpdateFilter<T> extends TaskDecorator<T> {
    /**
     * Ticker to keep track of ticks
     */
    private Ticker ticker;

    /**
     * Creates a new instance of the RegulatorDecorator class
     *
     * @param task
     *            Task to decorate
     * @param updateTicks
     *            Ticks between each frame update
     */
    public UpdateFilter(Task task, int updateTicks) {
        super(task);
        this.ticker = new Ticker(updateTicks, false);
    }

    /**
     * Starts the task and the regulator
     */
    @Override
    public void start() {
        task.start();
    }

    /**
     * Updates the decorated task only if the required time since the last
     * update has elapsed.
     */
    @Override
    public boolean doAction(T entity) {
        if (this.ticker.isReady()) {
            task.doAction(entity);
        }
        return false;
    }

    /**
     * Set number of ticks between updates.
     *
     * @param updateTicks
     *            number of ticks
     */
    public void setUpdateTicks(int updateTicks) {
        this.ticker = new Ticker(updateTicks, false);
    }

}