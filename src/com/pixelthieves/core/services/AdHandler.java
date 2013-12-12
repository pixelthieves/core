package com.pixelthieves.core.services;

/**
 * Created by Tomas on 12/11/13.
 */
public interface AdHandler {

    public void onAdClicked();
    public void onAdClosed();
    public void onAdFailed(String message);
}
