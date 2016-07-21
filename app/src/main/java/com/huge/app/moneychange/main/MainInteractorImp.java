package com.huge.app.moneychange.main;

import android.util.Log;

/**
 * Created by alice on 7/20/16.
 */
public class MainInteractorImp implements MainInteractor {
    private  MainRepository repository;

    public MainInteractorImp(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getChange(int dollarAmout) {
        Log.i("MainInteractorImp", "getChange");

        repository.getChange(dollarAmout);
    }
}
