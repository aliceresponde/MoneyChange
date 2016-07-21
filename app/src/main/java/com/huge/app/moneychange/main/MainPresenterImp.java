package com.huge.app.moneychange.main;

import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.main.event.MainEvent;
import com.huge.app.moneychange.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 7/20/16.
 */
public class MainPresenterImp  implements  MainPresenter{
    private MainView view;
    private EventBus eventBus;
    private MainInteractor interactor;

    public MainPresenterImp(MainView view, EventBus eventBus, MainInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }


    public MainView getView() {
        return view;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Subscribe
    @Override
    public void onEventMainThread(MainEvent event) {
        if (view != null){
            view.hideProgress();
            view.showUIElements();
            String error = event.getError();
            if (error != null){
                view.onGetCurrencyError(error);
            }else{
                if (event.getType() == MainEvent.GET && event.getRates() != null ){
                    view.displayChange(event.getRates(), event.getnDollars());
                }
                else {
                    view.onGetCurrencyError("No data");
                }
            }
        }
    }

    @Override
    public void getChange(int dollarAmout) {
        view.showProgress();
        view.hideUIElements();
        interactor.getChange(dollarAmout);
    }
}
