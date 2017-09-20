package com.luckycode.myanticalculadora.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by marcelocuevas on 9/18/17.
 */

public class Input {
    private String expression;
    private List<String> allOperators;
    private List<String> allowedOperators;

    public Input() {
        expression = "";
        allOperators = new ArrayList<>();
        allOperators.add("+");
        allOperators.add("-");
        allOperators.add("*");
        allOperators.add("/");
        setAllowedOperators();
    }

    private void addCharacter(String character) {
        expression += character;
    }

    public boolean containsAllOperators() {
        for (int i = 0; i < allowedOperators.size(); i++) {
            if (!expression.contains(allowedOperators.get(i)))
                return false;
        }
        return true;
    }

    private boolean lastCharacterIsAnOperator(String expression) {
        return expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*")
                || expression.endsWith("/");
    }

    private int generateAmountOperators() {
        return new Random().nextInt(4) + 1;
    }

    public boolean isExpressionValid() {
        return !lastCharacterIsAnOperator(expression);
    }

    public String getOperatorsString() {
        String result = "";
        for (int i = 0; i < allowedOperators.size(); i++) {
            result += allowedOperators.get(i) + " ";
        }
        return result;
    }

    public boolean isNumberInputValid(String number) {
        if (!(expression.endsWith("/") && number.equals("0"))) {
            addCharacter(number);
            return true;
        }
        return false;
    }

    public void clear() {
        expression = "";
    }

    public boolean isOperatorInputValid(String operator) {
        if (operator.equals("+") || operator.equals("-"))
            if (!lastCharacterIsAnOperator(expression)) {
                addCharacter(operator);
                return true;
            } else
                return false;
        else if (expression.isEmpty())
            return false;
        else if (!lastCharacterIsAnOperator(expression)) {
            addCharacter(operator);
            return true;
        }
        return false;
    }

    private void setAllowedOperators() {
        allowedOperators = new ArrayList<>();
        int amountOperators = generateAmountOperators();
        Random randomGenerator = new Random();
        for (int i = 0; i < amountOperators; i++) {
            int randomPosition = randomGenerator.nextInt(allOperators.size());
            if (!allowedOperators.contains(allOperators.get(randomPosition)))
                allowedOperators.add(allOperators.get(randomPosition));
        }
    }

    public boolean isEmpty() {
        return expression.isEmpty();
    }

    public String getExpression() {
        return expression;
    }

    public List<String> getAllowedOperators() {
        return allowedOperators;
    }


}
