package com.luckycode.myanticalculadora.listeners;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public interface GameListener{
    void updateTime(int secs);
    void gameOver(String message);
    void userWon();
    void onResultCalculated(long result);
}
