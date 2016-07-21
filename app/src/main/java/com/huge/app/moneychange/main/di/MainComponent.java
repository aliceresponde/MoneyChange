package com.huge.app.moneychange.main.di;

import com.huge.app.moneychange.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/20/16.
 */
@Singleton
@Component (modules = MainModule.class)
public interface MainComponent {
    MainPresenter getPresenter();
}
