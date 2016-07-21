package com.huge.app.moneychange.main;

import com.huge.app.moneychange.api.CurrencyService;
import com.huge.app.moneychange.api.LatestCuerrencyResponse;
import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.main.event.MainEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 7/20/16.
 */
public class MainRepositoryImp implements  MainRepository{
    private EventBus eventBus;
    private CurrencyService service;

    public MainRepositoryImp(EventBus eventBus, CurrencyService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getChange(final int dollarAmout) {
        Call<LatestCuerrencyResponse> call = service.search(CurrencyService.BASE, CurrencyService.SYMBOLS);
        Callback<LatestCuerrencyResponse> callback = new Callback<LatestCuerrencyResponse>() {
            @Override
            public void onResponse(Call<LatestCuerrencyResponse> call, Response<LatestCuerrencyResponse> response) {
                if (response.isSuccess()){
                    LatestCuerrencyResponse latesCurrencyResponse = response.body();

                    if (latesCurrencyResponse != null){
                        post(MainEvent.GET, null, latesCurrencyResponse.getRates() , dollarAmout);
                    }else{
                        post(MainEvent.GET, response.message() , null , dollarAmout);
                    }
                }else {
                    post(MainEvent.GET, "ERROR", null, dollarAmout);
                }
            }

            @Override
            public void onFailure(Call<LatestCuerrencyResponse> call, Throwable t) {
                post(MainEvent.GET, t.getLocalizedMessage(), null, dollarAmout);
            }
        };

        call.enqueue(callback);
    }

    private void post(int eventType, String error, LatestCuerrencyResponse.Rates rates, int nDollars) {
        MainEvent event = new MainEvent();
        event.setType(eventType);
        event.setError(error);
        event.setRates(rates);
        event.setnDollars(nDollars);
        eventBus.post(event);
    }
}
