package com.pixelthieves.core.behavior.task;


import com.pixelthieves.core.behavior.controller.ParentTaskController;

/**
 * Inner node of the behavior tree, a flow director node, that selects a child to be executed next.
 * <p/>
 * Sets a specific kind of TaskController for these kinds of tasks.
 */
public abstract class ParentTask<T> extends Task<T> {
    /**
     * TaskControler for the parent task.
     */
    protected ParentTaskController control;

    public ParentTask() {
        createController();
    }

    /**
     * Creates the TaskController.
     */
    private void createController() {
        this.control = new ParentTaskController(this);
    }

    /**
     * Gets the control reference
     */
    @Override
    public ParentTaskController getControl() {
        return control;
    }

    /**
     * Checks for the appropiate pre-state of the data
     */
    @Override
    public boolean checkConditions(T object) {
        return control.getSubtasks().size() > 0;
    }
}
