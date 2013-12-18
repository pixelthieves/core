package com.pixelthieves.core.logic;

public abstract class UpdateableAdapter implements Updateable{

    private boolean active = true;

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }
}
