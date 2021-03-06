package com.pixelthieves.core.services;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Tomas on 11/18/13.
 */
public interface GameService {
    public boolean canSingIn();

    public boolean isSignedIn();

    public void signIn(Callable handler);

    public void signOut();

    public void skipSignIn();

    public void submitScore(int score);

    public void submitAchievement(Achievement achievement);

    public void showAchievementsRequested();

    public void showLeaderboardsRequested();

    public Map<String, String> getLeaderboard();
}
