package com.huge.app.moneychange.main;

import com.huge.app.moneychange.main.event.MainEvent;

/**
 * Created by alice on 7/20/16.
 */
public interface MainPresenter {

    //    ================EventBus==============
    void onCreate();
    void onDestroy();
    void onEventMainThread(MainEvent event);


    //-------------------------------
    void  getChange(int dollarAmout );


}
