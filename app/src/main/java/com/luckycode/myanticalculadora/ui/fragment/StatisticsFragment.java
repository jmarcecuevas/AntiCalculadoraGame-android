package com.luckycode.myanticalculadora.ui.fragment;

import android.widget.TextView;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyActivity;
import com.luckycode.myanticalculadora.common.LuckyFragment;
import com.luckycode.myanticalculadora.presenter.StatisticsPresenter;
import com.luckycode.myanticalculadora.ui.viewModel.StatisticsView;

import butterknife.BindView;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public class StatisticsFragment extends LuckyFragment implements StatisticsView {
    @BindView(R.id.cantPlayedGames)
    TextView cantPlayedGames;
    @BindView(R.id.cantWonGames)
    TextView cantWonGames;
    @BindView(R.id.cantLostGames)
    TextView cantLostGames;
    @BindView(R.id.maxLevelValue)
    TextView maxLevel;
    private StatisticsPresenter presenter;

    @Override
    protected int layout() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void init() {
        presenter = new StatisticsPresenter(this, ((LuckyActivity) getActivity()).getHelper());
        presenter.getStatistics();
    }


    @Override
    public void showPlayedGames(int playedGames) {
        cantPlayedGames.setText(String.valueOf(playedGames));
    }

    @Override
    public void showWonGames(int wonGames) {
        cantWonGames.setText(String.valueOf(wonGames));
    }

    @Override
    public void showLostGames(int lostGames) {
        cantLostGames.setText(String.valueOf(lostGames));
    }

    @Override
    public void showMaxLevel(int maxLevel) {
        this.maxLevel.setText(String.valueOf(maxLevel));
    }
}