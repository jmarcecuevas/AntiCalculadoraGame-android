package com.luckycode.myanticalculadora.ui.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.listeners.MenuListener;
import com.luckycode.myanticalculadora.common.LuckyFragment;
import com.luckycode.myanticalculadora.utils.MusicManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by marcelocuevas on 9/14/17.
 */

public class MenuFragment extends LuckyFragment{
    @BindView(R.id.play) Button playButton;
    @BindView(R.id.ranking) Button rankingButton;
    @BindView(R.id.settings) Button settingsButton;
    @BindView(R.id.exit) Button exitButton;
    @BindView(R.id.arrow) ImageView arrowImage;

    private MenuListener menuListener;

    @Override
    protected int layout() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void init() {
        setAnimations();
    }

    public void setAnimations(){
        final Animation animShake=AnimationUtils.loadAnimation(getActivity(),R.anim.shake);
        arrowImage.startAnimation(animShake);
    }

    @OnClick({R.id.play,R.id.ranking,R.id.settings,R.id.exit})
    public void onClick(View view){
        MusicManager.start(getActivity(),3,true,false);
        switch (view.getId()){
            case R.id.play:
                menuListener.play();
                break;
            case R.id.ranking:
                break;
            case R.id.settings:
                menuListener.onChangeFragment(SettingsFragment.class);
                break;
            case R.id.exit:
                menuListener.exit();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playButton.clearAnimation();
        arrowImage.clearAnimation();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            menuListener=(MenuListener) context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}
