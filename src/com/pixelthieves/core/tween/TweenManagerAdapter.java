package com.pixelthieves.core.tween;

import aurelienribon.tweenengine.TweenManager;
import com.pixelthieves.core.logic.Updateable;

/**
 * User: Tomas <br> Date: 7/22/13 <br> Time: 2:32 PM <br>
 */
public class TweenManagerAdapter extends TweenManager implements Updateable {
    private boolean active = true;

    @Override
    public void update(float delta) {
        super.update(delta);
    }


    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
