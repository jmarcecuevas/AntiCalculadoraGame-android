package com.luckycode.myanticalculadora.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyFragment;
import com.luckycode.myanticalculadora.presenter.GamePresenter;
import com.luckycode.myanticalculadora.ui.viewModel.GameView;
import com.udojava.evalex.Expression;

import org.xml.sax.InputSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public class GameFragment extends LuckyFragment implements GameView{
    @BindViews({R.id.btn_add,R.id.btn_sub,R.id.btn_mul,R.id.btn_div})
        List<Button> operatorButtons;
    @BindView(R.id.btn_add)Button btnAdd;
    @BindView(R.id.btn_mul)Button btnMul;
    @BindView(R.id.btn_div)Button btnDiv;
    @BindView(R.id.btn_sub)Button btnSub;
    @BindView(R.id.tv_show)TextView tvShow;
    @BindView(R.id.randomNumberTV)TextView randomNumberTV;
    @BindView(R.id.allowedOperators)TextView allowedOperators;
    private GamePresenter gamePresenter;
    private List<String> operators;
    private int randomNumber;
    private String expression="";

    @Override
    protected int layout() {
        return R.layout.fragment_game;
    }

    @Override
    protected void init() {
        gamePresenter = new GamePresenter(this);
        randomNumber=gamePresenter.generateRandomNumber();
        randomNumberTV.setText(String.valueOf(randomNumber));
        operators=gamePresenter.generateAllowedOperators();
        allowedOperators.setText(getString(R.string.allowedOperators)+" "+
            gamePresenter.getOperatorsString(operators));
        setInitialButtonsState();
    }

    @OnClick({R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,
            R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9})
    public void numberClicked(Button button){
        gamePresenter.handleNumberInput(expression,button.getText().toString());
    }

    @OnClick({R.id.btn_add,R.id.btn_sub,R.id.btn_mul,R.id.btn_div})
    public void operatorClicked(Button button){
        gamePresenter.handleOperatorInput(expression,button.getText().toString());
    }

    @OnClick(R.id.btn_clear)
    public void clear(){
        expression="";
        tvShow.setText(expression);
    }

    @OnClick(R.id.btn_equal)
    public void go(){
        gamePresenter.evaluateFinalInput(expression);
    }

    public void setInitialButtonsState(){
        for(Button button:operatorButtons){
            if(!operators.contains(button.getText())){
                button.setClickable(false);
                button.setAlpha(0.4f);
            }
        }
    }

    @Override
    public void onValidInput(String character) {
        expression+=character;
        tvShow.setText(expression);
    }

    @Override
    public void onInvalidInput() {
        Log.e("INVALID","INPUT");
    }

    @Override
    public void onInputReadyToBeCalculated(String expression) {
        gamePresenter.calculateExpression(expression,operators);
    }

    @Override
    public void onWrongInputToBeCalculated() {
        Log.e("NO SE PUEDE","CALCULAR");
    }

    @Override
    public void showResult(String number) {
        tvShow.setText(number);
        gamePresenter.compareInputWithGameNumber(randomNumber, Integer.valueOf(number));
    }

    @Override
    public void userWon() {
        new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setMessage(getString(R.string.userWon))
                .setTitle(getString(R.string.userWonTitle))
                .setPositiveButton(getString(R.string.goOn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().recreate();
                    }
                }).show();
    }

    @Override
    public void showUserLostMessage(String message) {
        new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .setMessage(message)
                .setTitle(getString(R.string.userLostTitle))
                .setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().recreate();
                    }
                }).show();
    }
}
