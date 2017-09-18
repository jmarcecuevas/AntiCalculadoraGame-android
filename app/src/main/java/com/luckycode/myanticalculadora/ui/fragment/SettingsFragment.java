package com.luckycode.myanticalculadora.ui.fragment;

import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyFragment;
import com.luckycode.myanticalculadora.interactor.SettingsInteractor;
import com.luckycode.myanticalculadora.presenter.SettingsPresenter;
import com.luckycode.myanticalculadora.ui.viewModel.SettingsView;

import butterknife.BindView;

/**
 * Created by marcelocuevas on 9/16/17.
 */

public class SettingsFragment extends LuckyFragment implements SettingsView,CompoundButton.OnCheckedChangeListener{
    @BindView(R.id.musicSwitcher) SwitchCompat musicSwitcher;
    @BindView(R.id.soundsSwitcher) SwitchCompat soundsSwitcher;
    @BindView(R.id.vibrationSwitcher) SwitchCompat vibrationSwitcher;
    private SettingsPresenter presenter;


    @Override
    protected int layout() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void init() {
        SettingsInteractor settingsInteractor=new SettingsInteractor(presenter,getContext());
        presenter=new SettingsPresenter(this,settingsInteractor);
        musicSwitcher.setChecked(presenter.getMusicState());
        soundsSwitcher.setChecked(presenter.getSoundsState());
        vibrationSwitcher.setChecked(presenter.getVibrationState());
        musicSwitcher.setOnCheckedChangeListener(this);
        soundsSwitcher.setOnCheckedChangeListener(this);
        vibrationSwitcher.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.musicSwitcher:
                presenter.onMusicPreferencesChanged(b);
                break;
            case R.id.soundsSwitcher:
                presenter.onSoundsPreferencesChanged(b);
                break;
            case R.id.vibrationSwitcher:
                presenter.onVibrationPreferencesChanged(b);
                break;
        }
    }
}
