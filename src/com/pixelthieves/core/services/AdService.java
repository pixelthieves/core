package com.pixelthieves.core.services;

import java.util.concurrent.Callable;

/**
 * Created by Tomas on 12/7/13.
 */
public interface AdService {

    public enum AdType {
        Interestial, MoreApps
    }

    public void initAds();

    public void resume();

    public void pause();

    public void showAd(AdType adType);

    public void setHandler(AdHandler handler);
}
