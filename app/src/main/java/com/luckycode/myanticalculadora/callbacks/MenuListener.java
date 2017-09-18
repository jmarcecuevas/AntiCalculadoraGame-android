package com.luckycode.myanticalculadora.callbacks;

import android.support.v4.app.Fragment;

import com.luckycode.myanticalculadora.ui.fragment.SettingsFragment;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public interface MenuListener  {
    void exit();
    void play();
    void onChangeFragment(Class fragment);
}
