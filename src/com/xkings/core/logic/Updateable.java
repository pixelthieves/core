package com.xkings.core.logic;

public interface Updateable {

    void update(float delta);

    boolean isActive();

    void setActive(boolean active);
}
