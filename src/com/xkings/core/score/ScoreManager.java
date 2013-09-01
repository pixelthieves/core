package com.xkings.core.score;


import java.io.IOException;

/**
 * User: Tomas <br>
 * Date: 7/22/13 <br>
 * Time: 11:43 PM <br>
 */
public class ScoreManager {

    private int score;

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score += add;
    }

    public void publishScore(String name) throws IOException {
        // scoreoid.setPlayerScore(name, String.valueOf(score));
    }
}
