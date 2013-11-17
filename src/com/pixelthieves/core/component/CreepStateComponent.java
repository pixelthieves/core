package com.pixelthieves.core.component;

import com.artemis.Component;

/**
 * Created by Tomas on 9/8/13.
 */
public class CreepStateComponent extends Component {

    private Time time = new Time();

    private CreepState state = CreepState.IDLE;


    public Time getTime() {
        return time;
    }

    public CreepState getState() {
        return state;
    }

    public void setState(CreepState state) {
        this.state = state;
    }
}
