package com.xkings.core.component;

import com.artemis.Component;

/**
 * Created by Tomas on 9/8/13.
 */
public class CreepStateComponent extends Component {

    private TimeHolder timeHolder = new TimeHolder();

    private CreepState state = CreepState.IDLE;


    public TimeHolder getTimeHolder() {
        return timeHolder;
    }

    public CreepState getState() {
        return state;
    }

    public void setState(CreepState state) {
        this.state = state;
    }
}
