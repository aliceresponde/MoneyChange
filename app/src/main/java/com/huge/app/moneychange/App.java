package com.huge.app.moneychange;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
//        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Copernicus-Book.otf");
    }

    public MainComponent getMainComponent( MainView view) {
        return DaggerMainComponent
                .builder()
                .mainModule( new MainModule(view))
                .build();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
