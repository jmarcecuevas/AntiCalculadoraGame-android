package com.luckycode.myanticalculadora.ui.activity;

import android.content.Intent;

import com.luckycode.myanticalculadora.R;
import com.luckycode.myanticalculadora.common.LuckyActivity;
import com.luckycode.myanticalculadora.listeners.MenuListener;
import com.luckycode.myanticalculadora.ui.fragment.MenuFragment;

public class MenuActivity extends LuckyActivity implements MenuListener {

    @Override
    protected void init() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_menu;
    }

    @Override
    protected Class getFragmentToAdd() {
        return MenuFragment.class;
    }

    @Override
    protected int getFragmentLayout() {
        return R.id.fragment_menu_container;
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void play() {
        Intent intent = new Intent(this, GameActivity.class);
        overridePendingTransition(R.anim.pull_in_left, R.anim.pull_in_right);
        startActivity(intent);
        finish();
    }

    @Override
    public void onChangeFragment(Class fragment) {
        replaceFragmentWithBackStack(fragment, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
