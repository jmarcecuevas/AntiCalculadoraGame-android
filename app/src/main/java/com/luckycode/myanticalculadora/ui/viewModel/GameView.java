package com.luckycode.myanticalculadora.ui.viewModel;

/**
 * Created by marcelocuevas on 9/17/17.
 */

public interface GameView{
    void onValidInput(String character);
    void onInvalidInput();
    void onInputReadyToBeCalculated(String expression);
    void onWrongInputToBeCalculated();
    void showResult(String number);
    void userWon();
    void showUserLostMessage(String message);
}