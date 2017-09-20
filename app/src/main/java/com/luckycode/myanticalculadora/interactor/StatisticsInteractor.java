package com.luckycode.myanticalculadora.interactor;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.luckycode.myanticalculadora.common.LuckyInteractor;
import com.luckycode.myanticalculadora.model.Partida;
import com.luckycode.myanticalculadora.presenter.StatisticsPresenter;
import com.luckycode.myanticalculadora.utils.DatabaseHelper;

import java.sql.SQLException;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public class StatisticsInteractor extends LuckyInteractor<StatisticsPresenter> {
    private DatabaseHelper dbHelper;
    private StatisticsListener listener;
    private Dao dao;

    public StatisticsInteractor(StatisticsPresenter mPresenter, DatabaseHelper dbHelper,
                                StatisticsListener listener) {
        super(mPresenter);
        this.dbHelper = dbHelper;
        this.listener = listener;
        setDaoPartida();
    }

    public StatisticsInteractor(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
        setDaoPartida();
    }

    private void setDaoPartida() {
        try {
            dao = dbHelper.getPartidaDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void storePartida(boolean won, boolean lost, int level) {
        Partida partida = new Partida(won, lost, level);
        try {
            dao.create(partida);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPlayedGamesAmount() {
        try {
            listener.playedGamesAmount(dao.queryForAll().size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getWonGamesAmount() {
        try {
            listener.wonGamesAmount(dao.queryForEq("won", true).size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getLostGamesAmount() {
        try {
            listener.lostGamesAmount(dao.queryForEq("lost", true).size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMaxLevel() {
        QueryBuilder<Partida, Integer> qb = dao.queryBuilder();
        qb.orderBy("level", false); // descending sort
        qb.limit(1);
        try {
            Partida result=qb.queryForFirst();
            if(result!=null)
                listener.maxLevel(result.getLevel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public interface StatisticsListener {
        void playedGamesAmount(int amount);

        void wonGamesAmount(int amount);

        void lostGamesAmount(int amount);

        void maxLevel(int maxLevel);
    }

}
