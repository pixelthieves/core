package com.xkings.core.behavior.task;


import com.xkings.core.behavior.controller.TaskController;

/**
 * Leaf task (or node) in the behavior tree.
 * <p/>
 * Specifies a TaskControler, by composition, to take care of all the control logic,
 * without burdening the Task class with complications.
 */
public abstract class LeafTask<T> extends Task<T> {

    private final TaskController taskController;

    public LeafTask() {
        this.taskController = new TaskController(null);
        taskController.setTask(this);
    }

    @Override
    public TaskController getControl() {
        return taskController;
    }
}