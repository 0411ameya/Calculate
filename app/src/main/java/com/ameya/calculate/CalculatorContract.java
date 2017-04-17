package com.ameya.calculate;

/**
 * Created by 0411a on 4/6/2017.
 */

public interface CalculatorContract {
    interface PublishtoView {

        void showResult(String Result);


        void showToastMsg(String msg);



    }

    interface ForwardInputInteractionToPresenter {

        void onNumberClick(int number);
        void onOperatorClick(String operator);
        void onDecimalClick();
        void onEvaluateClick();

    }

    interface ForwardDisplayInteractionToPresenter {

        void onDeleteShortClick();

        void onDeleteLongClick();


    }



}
