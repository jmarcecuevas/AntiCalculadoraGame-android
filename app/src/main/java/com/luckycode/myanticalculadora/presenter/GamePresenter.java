package com.luckycode.myanticalculadora.presenter;

import android.content.Context;

import com.luckycode.myanticalculadora.common.LuckyPresenter;
import com.luckycode.myanticalculadora.interactor.SettingsInteractor;
import com.luckycode.myanticalculadora.interactor.StatisticsInteractor;
import com.luckycode.myanticalculadora.listeners.GameListener;
import com.luckycode.myanticalculadora.model.Game;
import com.luckycode.myanticalculadora.ui.viewModel.GameView;
import com.luckycode.myanticalculadora.utils.DatabaseHelper;

import java.util.List;

/**
 * Created by marcelocuevas on 9/17/17.
 */

public class GamePresenter extends LuckyPresenter<GameView> implements GameListener {
    private Game game;
    private StatisticsInteractor interactor;
    private SettingsInteractor settingsInteractor;

    public GamePresenter(GameView mView, DatabaseHelper dbHelper, Context context) {
        super(mView);
        game = new Game(this);
        interactor = new StatisticsInteractor(dbHelper);
        settingsInteractor = new SettingsInteractor(context);
    }

    public void startGame() {
        game.start();
        getView().showNumberToPlay(game.getNumberGame());
        getView().setInitialOperatorsText(game.getOperatorsString());
        getView().setInitialButtonsState(getAllowedOperators());
        getView().showLevelNumber(game.getLevel());
    }

    public void restartGame() {
        game.restart();
        startGame();
    }

    public void advanceToNewLevel() {
        game.advanceToNewLevel();
        startGame();
    }

    public void handleOperatorInput(String operator) {
        if (game.isOperatorInputValid(operator)) {
            getView().onValidInput(game.getExpression());
        } else {
            getView().onInvalidInput();
        }
    }

    public void handleNumberInput(String number) {
        if (game.isNumberInputValid(number)) {
            getView().onValidInput(game.getExpression());
        } else {
            getView().onInvalidInput();
        }
    }

    public void evaluateFinalInput() {
        if (game.isExpressionValid())
            getView().onInputReadyToBeCalculated(game.getExpression());
        else
            getView().onWrongInputToBeCalculated();
    }

    public boolean isMusicAllowed() {
        getView().setBackgroundMusic(settingsInteractor.isMusicAllowed());
        return settingsInteractor.isMusicAllowed();
    }

    public boolean areSoundsAllowed() {
        return settingsInteractor.isSoundAllowed();
    }

    public void clearInput() {
        game.clear();
    }

    private List<String> getAllowedOperators() {
        return game.getAllowedOperators();
    }

    public void evalExpression() {
        game.evalExpression();
    }

    public void compareInputWithGameNumber() {
        game.compareInputWithGameNumber();
    }

    @Override
    public void updateTime(int secs) {
        getView().updateTime(secs);
    }

    public void stopTimer() {
        game.stopTimer();
    }

    @Override
    public void gameOver(String message, int level) {
        getView().showUserLostMessage(message);
        interactor.storePartida(false, true, level);
    }

    @Override
    public void userWon(int level) {
        getView().userWon();
        interactor.storePartida(true, false, level);
    }

    @Override
    public void onResultCalculated(long result) {
        getView().showResult(String.valueOf(result));
    }

    @Override
    public void onLevelsCompleted() {
        getView().showFinalLevelDialog();
    }


}