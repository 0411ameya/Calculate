package com.ameya.calculate;

/**
 * Created by 0411a on 4/6/2017.
 */

public class CalculatorPresenter implements CalculatorContract.ForwardDisplayInteractionToPresenter,
        CalculatorContract.ForwardInputInteractionToPresenter, Calculation.CalculationResult {

    private CalculatorContract.PublishtoView publishResult;
    private Calculation calc;


    public CalculatorPresenter(CalculatorContract.PublishtoView publishResult) {
        this.publishResult = publishResult;
        calc = new Calculation();
        calc.setCalculationResultListener(this);
    }

    @Override
    public void onDeleteShortClick() {
        calc.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calc.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calc.appendNumber(Integer.toString(number));
    }

    @Override
    public void onOperatorClick(String operator) {
        calc.appendOperator(operator);
    }

    @Override
    public void onDecimalClick() {
        calc.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calc.performEvaluation();
    }

    @Override
    public void onExpressionChanged(String result, boolean successful) {
        if (successful)
            publishResult.showResult(result);
        else{
            publishResult.showToastMsg(result);
        }

    }
}
