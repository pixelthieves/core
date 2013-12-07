package com.pixelthieves.core.services;

import java.util.concurrent.Callable;

/**
 * Created by Tomas on 12/7/13.
 */
public interface AdService {

    public void initAds();
    public void showAd(Callable handler);
}
