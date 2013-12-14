package com.pixelthieves.core.services;

/**
 * Created by Tomas on 12/7/13.
 */
public interface AdService {

    public enum AdType {
        Interestial, MoreApps
    }

    public void onCreate();

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void cacheAd(AdType adType);

    public void showAd(AdType adType);

    public void setAdHandler(AdHandler handler);
}
