package com.luckycode.myanticalculadora.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.luckycode.myanticalculadora.model.Partida;

import java.sql.SQLException;

/**
 * Created by marcelocuevas on 9/19/17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "partidas.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Partida, Integer> daoPartida;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Partida.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database, connectionSource);
    }

    public Dao<Partida, Integer> getPartidaDao() throws SQLException {
        if (daoPartida == null) {
            daoPartida = getDao(Partida.class);
        }
        return daoPartida;
    }

    @Override
    public void close() {
        super.close();
        daoPartida = null;
    }
}
