package com.luckycode.myanticalculadora.interactor;

import android.content.Context;
import android.content.SharedPreferences;

import com.luckycode.myanticalculadora.common.LuckyInteractor;
import com.luckycode.myanticalculadora.presenter.SettingsPresenter;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public class SettingsInteractor extends LuckyInteractor<SettingsPresenter> {
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private final String PREF_NAME = "MyPref";
    private final String KEY_MUSIC = "music";
    private final String KEY_SOUNDS = "sounds";

    public SettingsInteractor(SettingsPresenter mPresenter, Context context) {
        super(mPresenter);
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public SettingsInteractor(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void storeInSharedPreferences(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void storeMusicState(boolean value) {
        editor.putBoolean(KEY_MUSIC, value);
        editor.commit();
    }

    public void storeSoundsState(boolean value) {
        editor.putBoolean(KEY_SOUNDS, value);
        editor.commit();
    }

    public boolean isMusicAllowed() {
        return pref.getBoolean(KEY_MUSIC, true);
    }

    public boolean isSoundAllowed() {
        return pref.getBoolean(KEY_SOUNDS, true);
    }


}
