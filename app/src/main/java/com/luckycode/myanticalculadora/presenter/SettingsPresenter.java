package com.luckycode.myanticalculadora.presenter;

import android.content.Context;
import android.util.Log;

import com.luckycode.myanticalculadora.common.LuckyPresenter;
import com.luckycode.myanticalculadora.interactor.SettingsInteractor;
import com.luckycode.myanticalculadora.ui.viewModel.SettingsView;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public class SettingsPresenter extends LuckyPresenter<SettingsView> {
    private SettingsInteractor settingsInteractor;

    public SettingsPresenter(SettingsView mView,SettingsInteractor settingsInteractor) {
        super(mView);
        this.settingsInteractor=settingsInteractor;
    }

    public void onMusicPreferencesChanged(boolean newState){
        settingsInteractor.storeMusicState(newState);
    }

    public void onSoundsPreferencesChanged(boolean newState){
        settingsInteractor.storeSoundsState(newState);
    }

    public void onVibrationPreferencesChanged(boolean newState){
        settingsInteractor.storeVibrationState(newState);
    }

    public boolean getMusicState(){
        return settingsInteractor.isMusicChecked();
    }

    public boolean getSoundsState(){
        return settingsInteractor.isSoundChecked();
    }

    public boolean getVibrationState(){
        return settingsInteractor.isVibrationChecked();
    }


}
