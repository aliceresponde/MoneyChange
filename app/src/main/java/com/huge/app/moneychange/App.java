package com.huge.app.moneychange;

import android.app.Application;

import com.huge.app.moneychange.main.di.DaggerMainComponent;
import com.huge.app.moneychange.main.di.MainComponent;
import com.huge.app.moneychange.main.di.MainModule;
import com.huge.app.moneychange.main.ui.MainView;

/**
 * Created by alice on 7/20/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MainComponent getMainComponent( MainView view) {
        return DaggerMainComponent
                .builder()
                .mainModule( new MainModule(view))
                .build();

//        return null;
    }
}
