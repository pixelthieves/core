package com.xkings.core.behavior.task;


/**
 * Sequence is logical <tt>AND</tt> over child task.
 * <p/>
 * Loops through all children one by one and fails if one children returns {@code false}
 * on conditions. Selector succeeds if all children succeed.
 */
public class Sequence<T> extends ParentTask<T> {

    @Override
    public boolean doAction(T object) {
        for (Task t : control.getSubtasks()) {
            t.start();
            if (!t.checkConditions(object) || !t.doAction(object)) {
                return false;
            }
        }
        return true;
    }
}
