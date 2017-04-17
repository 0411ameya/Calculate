package com.ameya.calculate;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

/**
 * Created by 0411a on 4/6/2017.
 */

public class Calculation {
    private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentExpression;

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }

    public void setCalculationResultListener(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
    }

    public Calculation() {
        symbols = new Symbols();
    }

    public void deleteCharacter() {
        if (currentExpression.length() > 0){
            currentExpression = currentExpression.substring(0, currentExpression.length()-1);
            calculationResult.onExpressionChanged(currentExpression,true);
        }
        else
            calculationResult.onExpressionChanged("Invalid Input", false);
    }
    public void deleteExpression() {
        if (currentExpression.equals("")){
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
        currentExpression = "";
        calculationResult.onExpressionChanged("", true);
    }
    public void appendNumber(String number) {
        if(currentExpression.startsWith("0")&& number.equals("0")){
            calculationResult.onExpressionChanged("Invalid Input", false);
        }else {
            if (currentExpression.length() < 17)
            {
                currentExpression += number;
                calculationResult.onExpressionChanged(currentExpression, true);
            }
            else
                calculationResult.onExpressionChanged("Expression too long", false);

        }
    }
    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)){
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }
    public void appendDecimal() {
        if (validateExpression(currentExpression)){
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }
    public void performEvaluation() {
        if (validateExpression(currentExpression)){
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
            } catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Invalid Input", false);
                e.printStackTrace();
            }
        }
    }

    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")
                ) {
            calculationResult.onExpressionChanged("Invalid Input", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Empty Expression", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expression Too Long", false);
            return false;
        } else {
            return true;
        }
    }




}
