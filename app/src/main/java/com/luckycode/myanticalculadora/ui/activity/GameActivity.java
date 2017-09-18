package com.luckycode.myanticalculadora.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyActivity;
import com.luckycode.myanticalculadora.ui.fragment.GameFragment;
import com.luckycode.myanticalculadora.ui.fragment.SettingsFragment;

import butterknife.BindView;

public class GameActivity extends LuckyActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.bottom_navigation)BottomNavigationView bottomNavigation;

    @Override
    protected void init() {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_game;
    }

    @Override
    protected Class getFragmentToAdd() {
        return GameFragment.class;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.game_container;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_game:
                replaceFragmentWithBackStack(GameFragment.class,false);
                return true;
            case R.id.navigation_ranking:
                return true;
            case R.id.navigation_settings:
                replaceFragmentWithBackStack(SettingsFragment.class,false);
                return true;
            case R.id.navigation_exit:
                finish();
                return true;
        }
        return false;
    }




}
