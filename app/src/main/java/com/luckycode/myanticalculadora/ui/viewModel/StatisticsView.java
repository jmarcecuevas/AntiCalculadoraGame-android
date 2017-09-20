package com.luckycode.myanticalculadora.ui.viewModel;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public interface StatisticsView {
    void showPlayedGames(int playedGames);

    void showWonGames(int wonGames);

    void showLostGames(int lostGames);

    void showMaxLevel(int maxLevel);
}