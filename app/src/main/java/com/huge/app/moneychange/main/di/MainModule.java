package com.huge.app.moneychange.main.di;

import com.huge.app.moneychange.api.CurrencyClient;
import com.huge.app.moneychange.api.CurrencyService;
import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.libs.GreenRobotsEventBus;
import com.huge.app.moneychange.main.MainInteractor;
import com.huge.app.moneychange.main.MainInteractorImp;
import com.huge.app.moneychange.main.MainPresenter;
import com.huge.app.moneychange.main.MainPresenterImp;
import com.huge.app.moneychange.main.MainRepository;
import com.huge.app.moneychange.main.MainRepositoryImp;
import com.huge.app.moneychange.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/20/16.
 * proveer el presenter
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

//    ============================Presenter========================

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, MainInteractor interactor){
        return  new MainPresenterImp(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    MainView providesMainView (){
        return  this.view;
    }

    /**
     * Crea un new GreenRobotsEventBus a partir del mio
     * @param eventBus
     * @return
     */
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus ){
        return new GreenRobotsEventBus(eventBus);
    }

    /**
     * Retorna unstancia del GreenRobotEventBus
     * @return
     */
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    MainInteractor providesMainInteractor(MainRepository repository){
        return  new MainInteractorImp(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, CurrencyService service){
        return new MainRepositoryImp(eventBus , service);
    }

    @Provides
    @Singleton
    CurrencyService providesCurrencyService(){
        return  new CurrencyClient().getCurrencyService();
    }

}
