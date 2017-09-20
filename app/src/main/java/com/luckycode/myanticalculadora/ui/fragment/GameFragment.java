package com.luckycode.myanticalculadora.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyActivity;
import com.luckycode.myanticalculadora.common.LuckyFragment;
import com.luckycode.myanticalculadora.presenter.GamePresenter;
import com.luckycode.myanticalculadora.ui.viewModel.GameView;
import com.luckycode.myanticalculadora.utils.MusicManager;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public class GameFragment extends LuckyFragment implements GameView {
    @BindViews({R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div})
    List<Button> operatorButtons;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_mul)
    Button btnMul;
    @BindView(R.id.btn_div)
    Button btnDiv;
    @BindView(R.id.btn_sub)
    Button btnSub;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.randomNumberTV)
    TextView randomNumberTV;
    @BindView(R.id.allowedOperators)
    TextView allowedOperators;
    @BindView(R.id.time)
    TextView timeTextView;
    @BindView(R.id.levelTV)
    TextView levelTV;
    private GamePresenter gamePresenter;
    private boolean musicAllowed, soundsAllowed;

    @Override
    protected int layout() {
        return R.layout.fragment_game;
    }

    @Override
    protected void init() {
        gamePresenter = new GamePresenter(this, ((LuckyActivity) getActivity()).getHelper(), getContext());
        gamePresenter.startGame();
        musicAllowed = gamePresenter.isMusicAllowed();
        soundsAllowed = gamePresenter.areSoundsAllowed();
    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    public void numberClicked(Button button) {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 3, true, false);
        gamePresenter.handleNumberInput(button.getText().toString());
    }

    @OnClick({R.id.btn_add, R.id.btn_sub, R.id.btn_mul, R.id.btn_div})
    public void operatorClicked(Button button) {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 3, true, false);
        gamePresenter.handleOperatorInput(button.getText().toString());
    }

    @OnClick(R.id.btn_clear)
    public void clear() {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 3, true, false);
        gamePresenter.clearInput();
        tvShow.setText("");
    }

    @OnClick(R.id.btn_equal)
    public void go() {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 3, true, false);
        gamePresenter.evaluateFinalInput();
    }

    @Override
    public void setInitialButtonsState(List<String> operators) {
        for (Button button : operatorButtons)
            if (!operators.contains(button.getText())) {
                button.setClickable(false);
                button.setAlpha(0.4f);
            } else {
                button.setClickable(true);
                button.setAlpha(1.0f);
            }
    }

    @Override
    public void setInitialOperatorsText(String operators) {
        allowedOperators.setText(getString(R.string.allowedOperators) + " " +
                operators);
    }

    @Override
    public void showLevelNumber(int levelNumber) {
        levelTV.setText(String.valueOf(levelNumber));
    }

    @Override
    public void showNumberToPlay(int number) {
        randomNumberTV.setText(String.valueOf(number));
    }

    @Override
    public void onValidInput(String expression) {
        tvShow.setText(expression);
    }

    @Override
    public void onInvalidInput() {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 4, true, false);
    }

    @Override
    public void onInputReadyToBeCalculated(String expression) {
        gamePresenter.evalExpression();
    }

    @Override
    public void onWrongInputToBeCalculated() {
        if (soundsAllowed)
            MusicManager.start(getActivity(), 4, true, false);
    }

    @Override
    public void showResult(String number) {
        tvShow.setText(number);
        gamePresenter.compareInputWithGameNumber();
    }

    @Override
    public void userWon() {
        MusicManager.pause(0);
        if (soundsAllowed)
            MusicManager.start(getActivity(), 1, true, false);
        if (!getActivity().isFinishing())
            new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setMessage(getString(R.string.userWon))
                    .setTitle(getString(R.string.userWonTitle))
                    .setPositiveButton(getString(R.string.goOn), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            clear();
                            if (musicAllowed)
                                MusicManager.start(getActivity(), 0, true, true);
                            gamePresenter.advanceToNewLevel();
                        }
                    }).show();
    }

    @Override
    public void showUserLostMessage(String message) {
        MusicManager.pause(0);
        if (soundsAllowed)
            MusicManager.start(getActivity(), 2, true, false);
        if (!getActivity().isFinishing())
            new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setMessage(message)
                    .setTitle(getString(R.string.userLostTitle))
                    .setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (musicAllowed)
                                MusicManager.start(getActivity(), 0, true, true);
                            clear();
                            gamePresenter.restartGame();
                        }
                    }).show();
    }

    @Override
    public void updateTime(int time) {
        timeTextView.setText(String.valueOf(time));
    }

    @Override
    public void setBackgroundMusic(boolean musicAllowed) {
        MusicManager.pause();
        if (musicAllowed) {
            MusicManager.start(getActivity(), 0, true, true);
        }
    }

    @Override
    public void showFinalLevelDialog() {
        MusicManager.pause(0);
        if (soundsAllowed)
            MusicManager.start(getActivity(), 1, true, false);
        if (!getActivity().isFinishing())
            new AlertDialog.Builder(getContext())
                    .setCancelable(false)
                    .setMessage(getString(R.string.levelsCompleted))
                    .setTitle(getString(R.string.userWonTitle))
                    .setPositiveButton(getString(R.string.goOn), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            clear();
                            if (musicAllowed)
                                MusicManager.start(getActivity(), 0, true, true);
                            gamePresenter.restartGame();
                        }
                    }).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        gamePresenter.stopTimer();
    }
}
