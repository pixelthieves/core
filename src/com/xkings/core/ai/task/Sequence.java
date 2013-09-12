package com.xkings.core.ai.task;


import com.artemis.Entity;

/**
 * Sequence is logical <tt>AND</tt> over child task.
 * 
 * Loops through all children one by one and fails if one children returns
 * {@code false} on conditions. Selector succeeds if all children succeed.
 * 
 */
public class Sequence extends ParentTask {

    @Override
    public boolean doAction(Entity entity) {
        for (Task t : control.getSubtasks()) {
            t.start(entity);
            if (!t.checkConditions(entity) || !t.doAction(entity)) {
                return false;
            }
        }
        return true;
    }
}
