package com.xkings.core.behavior.task;


/**
 * Selector is logical <tt>OR</tt> over child task.
 * <p/>
 * Loops through all children one by one and selects first that returns {@code true} on conditions. Selector fails if
 * all children fail.
 */
public class Selector<T> extends ParentTask<T> {

    @Override
    public boolean doAction(T object) {
        for (Task t : control.getSubtasks()) {
            t.start();
            if (t.checkConditions(object) && t.doAction(object)) {
                return true;
            }
        }
        return false;
    }
}
