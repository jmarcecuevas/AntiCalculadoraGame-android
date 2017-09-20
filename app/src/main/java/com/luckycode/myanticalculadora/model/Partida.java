package com.luckycode.myanticalculadora.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by marcelocuevas on 9/19/17.
 */

@DatabaseTable(tableName = "Partida")
public class Partida {
    @DatabaseField
    private boolean won;
    @DatabaseField
    private boolean lost;
    @DatabaseField
    private int level;

    public Partida(boolean won, boolean lost, int level) {
        this.won = won;
        this.lost = lost;
        this.level = level;
    }

    public Partida() {

    }

    public int getLevel() {
        return level;
    }
}
