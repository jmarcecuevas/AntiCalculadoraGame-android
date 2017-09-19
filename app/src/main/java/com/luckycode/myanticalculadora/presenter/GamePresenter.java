package com.luckycode.myanticalculadora.presenter;

import android.util.Log;

import com.luckycode.myanticalculadora.common.LuckyPresenter;
import com.luckycode.myanticalculadora.listeners.GameListener;
import com.luckycode.myanticalculadora.model.Game;
import com.luckycode.myanticalculadora.model.Input;
import com.luckycode.myanticalculadora.ui.viewModel.GameView;
import java.util.List;

/**
 * Created by marcelocuevas on 9/17/17.
 */

public class GamePresenter extends LuckyPresenter<GameView> implements GameListener {
    private Game game;

    public GamePresenter(GameView mView) {
        super(mView);
        game=new Game(this);
    }

    public void startGame(){
        game.start();
        getView().showNumberToPlay(game.getNumberGame());
        getView().setInitialOperatorsText(game.getOperatorsString());
        getView().setInitialButtonsState(getAllowedOperators());
        getView().showLevelNumber(game.getLevel());
    }

    public void restartGame(){
        game.restart();
        startGame();
    }

    public void advanceToNewLevel(){
        game.advanceToNewLevel();
        startGame();
    }

    public void handleOperatorInput(String operator){
        if(game.isOperatorInputValid(operator)){
            getView().onValidInput(game.getExpression());
        }else{
            getView().onInvalidInput();
        }
    }

    public void handleNumberInput(String number){
        if(game.isNumberInputValid(number)){
            getView().onValidInput(game.getExpression());
        }else {
            getView().onInvalidInput();
        }
    }

    public void evaluateFinalInput(){
        if(game.isExpressionValid())
            getView().onInputReadyToBeCalculated(game.getExpression());
        else
            getView().onWrongInputToBeCalculated();
    }

    public void clearInput(){
        game.clear();
    }

    private List<String> getAllowedOperators(){
        return game.getAllowedOperators();
    }

    public void evalExpression(){
        game.evalExpression();
    }

    public void compareInputWithGameNumber(){
        game.compareInputWithGameNumber();
    }

    @Override
    public void updateTime(int secs) {
        getView().updateTime(secs);
    }

    @Override
    public void gameOver(String message) {
        getView().showUserLostMessage(message);
    }

    @Override
    public void userWon() {
        getView().userWon();
    }

    @Override
    public void onResultCalculated(long result) {
        getView().showResult(String.valueOf(result));
    }

}