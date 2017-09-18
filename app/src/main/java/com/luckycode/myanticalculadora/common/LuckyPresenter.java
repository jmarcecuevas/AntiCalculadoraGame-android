package com.luckycode.myanticalculadora.common;

/**
 * Created by marcelocuevas on 9/14/17.
 */

public class LuckyPresenter<T> {

    private T mView;

    public LuckyPresenter(T mView){
        this.mView=mView;
    }

    protected T getView(){
        return mView;
    }

    public void detachView(){
        mView=null;
    }
}