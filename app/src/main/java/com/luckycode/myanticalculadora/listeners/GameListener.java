package com.luckycode.myanticalculadora.listeners;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public interface GameListener {
    void updateTime(int secs);

    void gameOver(String message, int level);

    void userWon(int level);

    void onResultCalculated(long result);

    void onLevelsCompleted();
}
