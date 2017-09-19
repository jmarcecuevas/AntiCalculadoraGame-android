package com.luckycode.myanticalculadora.model;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;

import com.luckycode.myanticalculadora.listeners.GameListener;

import java.util.List;
import java.util.Random;

/**
 * Created by marcelocuevas on 9/18/17.
 */

public class Game{
    private static final int INITIAL_LEVEL=1;
    private static final int INITIAL_SECS=51;

    private Input input;
    private int numberGame;
    private int level;
    private CountDownTimer timer;
    private GameListener gameListener;
    private int timeInSecs;

    public Game(GameListener gameListener){
        input=new Input();
        level=INITIAL_LEVEL;
        timeInSecs=INITIAL_SECS;
        this.gameListener=gameListener;
    }

    public void start(){
        numberGame=generateNumberGame();
        numberGame=generateNumberGame();
        startTimer();
    }

    private long calculateExpression(){
        return Calculator.calculateExpression(input.getExpression());
    }

    public void advanceToNewLevel(){
        input=new Input();
        level++;
        timeInSecs-=5;
    }

    public void restart(){
        input=new Input();
        level=INITIAL_LEVEL;
        timeInSecs=INITIAL_SECS;
    }

    public int generateNumberGame(){
        Random randomGenerator=new Random();
        return (randomGenerator.nextInt(300)+200)*level;
    }

    public boolean isOperatorInputValid(String operator){
        return input.isOperatorInputValid(operator);
    }

    public boolean isNumberInputValid(String number){
        return input.isNumberInputValid(number);
    }

    public boolean isExpressionValid(){
        return input.isExpressionValid();
    }

    public void clear(){
        input.clear();
    }

    public void evalExpression(){
        if(!input.isEmpty()) {
            if (!input.containsAllOperators()) {
                timer.cancel();
                gameListener.gameOver("Lo siento,la expresión que ingresaste no utiliza todos los operadores que debe utilizar.");
            }else
                gameListener.onResultCalculated(calculateExpression());
        }
    }

    private void startTimer(){
        timer=new CountDownTimer(timeInSecs*1000,1000) {
            @Override
            public void onTick(long l) {
                gameListener.updateTime(Double.valueOf(l/1000.0).intValue());
            }
            @Override
            public void onFinish() {
                gameListener.gameOver("Lo siento,te has quedado sin tiempo.");
            }
        }.start();
    }

    public void compareInputWithGameNumber(){
        Log.e("llamo","al cancel");
        timer.cancel();
        if(numberGame==calculateExpression())
            gameListener.userWon();
        else
            gameListener.gameOver("Lo siento, el resultado de la expresión que ingresaste no coincide con el número que debía coincidir.");
    }

    public int getNumberGame() {
        return numberGame;
    }

    public String getExpression(){
        return input.getExpression();
    }

    public List<String> getAllowedOperators(){
        return input.getAllowedOperators();
    }

    public String getOperatorsString(){
        return input.getOperatorsString();
    }

    public int getLevel() {
        return level;
    }
}
