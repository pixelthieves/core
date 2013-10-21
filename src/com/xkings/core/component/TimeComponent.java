package com.xkings.core.component;

import com.artemis.Component;
import com.artemis.EntitySystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 9/8/13.
 */
public class TimeComponent extends Component {

    private final Map<Class<? extends EntitySystem>, Time> map = new HashMap<Class<? extends EntitySystem>, Time>();

    public Time getTime(Class<? extends EntitySystem> clazz) {
        Time result = map.get(clazz);
        if (result == null) {
            result = new Time();
            map.put(clazz, result);
        }
        return result;
    }
}
