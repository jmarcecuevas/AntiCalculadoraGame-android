package com.luckycode.myanticalculadora.presenter;

import com.luckycode.myanticalculadora.common.LuckyPresenter;
import com.luckycode.myanticalculadora.ui.viewModel.GameView;
import com.udojava.evalex.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by marcelocuevas on 9/17/17.
 */

public class GamePresenter extends LuckyPresenter<GameView>{
    private List<String> operators;

    public GamePresenter(GameView mView) {
        super(mView);
        operators=new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
    }

    public int generateRandomNumber() {
        Random randomGenerator=new Random();
        return randomGenerator.nextInt(500)+50;
    }

    public List<String> generateAllowedOperators(){
        int amountOperators=generateAmountOperators();
        List<String> allowedOperators=new ArrayList<>();
        Random randomGenerator=new Random();
        for(int i=0;i<amountOperators;i++){
            int randomPosition=randomGenerator.nextInt(operators.size());
            if(!allowedOperators.contains(operators.get(randomPosition)))
                allowedOperators.add(operators.get(randomPosition));
        }
        return allowedOperators;
    }

    public String getOperatorsString(List<String> operators){
        String result="";
        for(int i=0;i<operators.size();i++){
            result+=operators.get(i)+" ";
        }
        return result;
    }


    private int generateAmountOperators(){
        return new Random().nextInt(4)+1;
    }

    public void handleOperatorInput(String expression,String operator){
        if(operator.equals("+")||operator.equals("-"))
            if(!lastCharacterIsAnOperator(expression) )
                getView().onValidInput(operator);
            else
                getView().onInvalidInput();
        else if(expression.isEmpty())
            getView().onInvalidInput();
        else if(!lastCharacterIsAnOperator(expression))
            getView().onValidInput(operator);
    }

    public void handleNumberInput(String expression,String number){
        if(expression.endsWith("/") && number.equals("0"))
            getView().onInvalidInput();
        else
            getView().onValidInput(number);
    }

    private boolean lastCharacterIsAnOperator(String expression){
        return expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*")
            || expression.endsWith("/");
    }

    public void evaluateFinalInput(String expression){
        if(lastCharacterIsAnOperator(expression)){
            getView().onWrongInputToBeCalculated();
        }else{
            getView().onInputReadyToBeCalculated(expression);
        }
    }

    public void calculateExpression(String expression,List<String> operators){
        if (!expression.isEmpty()) {
            if(!expressionContainsAllOperators(expression,operators)){
                getView().showUserLostMessage("Lo siento,la expresión que ingresaste no utiliza todos los operadores que debe utilizar.");
            }else {
                Expression mathExpression = new Expression(expression);
                getView().showResult(String.valueOf(mathExpression.eval().setScale(100).longValueExact()));
            }
        }
    }

    private boolean expressionContainsAllOperators(String expression,List<String> operators){
        for(int i=0;i<operators.size();i++){
            if(!expression.contains(operators.get(i)))
                return false;
        }
        return true;
    }

    public void compareInputWithGameNumber(int originalNumber,long inputNumber){
        if(originalNumber==inputNumber){
            getView().userWon();
        }else{
            getView().showUserLostMessage("Lo siento, el resultado de la expresión que ingresaste no coincide con el número que debía coincidir.");
        }
            
            
    }


}