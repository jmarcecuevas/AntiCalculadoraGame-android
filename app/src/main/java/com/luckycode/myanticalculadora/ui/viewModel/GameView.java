package com.luckycode.myanticalculadora.ui.viewModel;

import java.util.List;

/**
 * Created by marcelocuevas on 9/17/17.
 */

public interface GameView{
    void showNumberToPlay(int number);
    void setInitialButtonsState(List<String> operators);
    void setInitialOperatorsText(String operators);
    void showLevelNumber(int levelNumber);
    void onValidInput(String expression);
    void onInvalidInput();
    void onInputReadyToBeCalculated(String expression);
    void onWrongInputToBeCalculated();
    void showResult(String number);
    void userWon();
    void showUserLostMessage(String message);
    void updateTime(int secs);
}