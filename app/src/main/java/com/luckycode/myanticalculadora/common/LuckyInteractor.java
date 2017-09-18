package com.luckycode.myanticalculadora.common;

import com.luckycode.myanticalculadora.common.LuckyPresenter;

/**
 * Created by marcelocuevas on 9/14/17.
 */

public abstract class LuckyInteractor<T extends LuckyPresenter>{

    T mPresenter;

    public LuckyInteractor(T mPresenter){
        this.mPresenter=mPresenter;
    }
}