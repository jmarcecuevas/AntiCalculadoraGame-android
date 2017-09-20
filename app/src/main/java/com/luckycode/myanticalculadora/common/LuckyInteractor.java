package com.luckycode.myanticalculadora.common;

/**
 * Created by marcelocuevas on 9/14/17.
 */

public abstract class LuckyInteractor<T extends LuckyPresenter> {

    private T mPresenter;

    public LuckyInteractor(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    public LuckyInteractor() {

    }
}