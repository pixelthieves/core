package com.pixelthieves.core.behavior.task;


import com.pixelthieves.core.behavior.controller.TaskController;

/**
 * Base class for the specific decorators. Decorates all the task methods except for the DoAction, for commodity.
 * (Tough
 * any method can be decorated in the base classes with no problem, they are decorated by default so the programmer
 * does
 * not forget)
 */
public abstract class TaskDecorator<T> extends Task<T> {
    /**
     * Reference to the task to decorate
     */
    protected Task task;

    /**
     * Creates a new instance of the Decorator class
     *
     * @param task Task to decorate
     */
    public TaskDecorator(Task task) {
        InitTask(task);
    }

    /**
     * Initializes the task reference
     *
     * @param task Task to decorate
     */
    private void InitTask(Task task) {
        this.task = task;
        this.task.getControl().setTask(this);
    }

    /**
     * Decorate the CheckConditions
     */
    @Override
    public boolean checkConditions(T entity) {
        return this.task.checkConditions(entity);
    }

    /**
     * Decorate the end
     */
    @Override
    public void end() {
        this.task.end();
    }

    /**
     * Decorate the request for the Control reference
     */
    @Override
    public TaskController getControl() {
        return this.task.getControl();
    }

    /**
     * Returns decorated task
     *
     * @return decorated task
     */
    public Task getTask() {
        return task;
    }

}
