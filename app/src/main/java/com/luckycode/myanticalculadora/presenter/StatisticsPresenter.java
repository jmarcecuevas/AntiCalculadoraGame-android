package com.luckycode.myanticalculadora.presenter;

import com.luckycode.myanticalculadora.common.LuckyPresenter;
import com.luckycode.myanticalculadora.interactor.StatisticsInteractor;
import com.luckycode.myanticalculadora.ui.viewModel.StatisticsView;
import com.luckycode.myanticalculadora.utils.DatabaseHelper;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public class StatisticsPresenter extends LuckyPresenter<StatisticsView> implements StatisticsInteractor.StatisticsListener {
    private StatisticsInteractor interactor;

    public StatisticsPresenter(StatisticsView mView, DatabaseHelper dbHelper) {
        super(mView);
        interactor = new StatisticsInteractor(this, dbHelper, this);
    }

    public void getStatistics() {
        interactor.getPlayedGamesAmount();
        interactor.getWonGamesAmount();
        interactor.getLostGamesAmount();
        interactor.getMaxLevel();
    }

    @Override
    public void playedGamesAmount(int amount) {
        getView().showPlayedGames(amount);
    }

    @Override
    public void wonGamesAmount(int amount) {
        getView().showWonGames(amount);
    }

    @Override
    public void lostGamesAmount(int amount) {
        getView().showLostGames(amount);
    }

    @Override
    public void maxLevel(int maxLevel) {
        getView().showMaxLevel(maxLevel);
    }
}
