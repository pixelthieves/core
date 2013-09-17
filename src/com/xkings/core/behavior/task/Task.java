package com.xkings.core.behavior.task;


import com.artemis.Entity;
import com.xkings.core.behavior.controller.TaskController;

public abstract class Task<T> {

    /**
     * Holds current object for processing.
     */
    protected T object;

    /**
     * Override to do a pre-conditions check to see if the task can be updated.
     *
     * @return True if it can, false if it can't
     */
    public abstract boolean checkConditions(T entity);

    /**
     * Override to add startup logic to the task
     */
    public void start() {

    }

    /**
     * Override to add ending logic to the task
     */
    public void end() {

    }

    /**
     * Override to specify the logic the task must update each cycle
     */
    public abstract boolean doAction(T entity);

    /**
     * Override to specify the controller the task has
     *
     * @return The specific task controller.
     */
    public abstract TaskController getControl();
}
