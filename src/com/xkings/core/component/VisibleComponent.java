package com.xkings.core.component;

import com.artemis.Component;

public class VisibleComponent extends Component {

    private boolean visible;

    public VisibleComponent(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
